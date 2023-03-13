package org.greenstand.android.TreeTracker.models

import org.greenstand.android.TreeTracker.models.location.Convergence
import org.greenstand.android.TreeTracker.models.location.LocationDataCapturer
import org.greenstand.android.TreeTracker.usecases.CreateTreeUseCase
import java.io.File
import java.util.*

class TreeCapturer(
    private val locationDataCapturer: LocationDataCapturer,
    private val stepCounter: StepCounter,
    private val createTreeUseCase: CreateTreeUseCase,
    private val deviceOrientation: DeviceOrientation,
    private val sessionTracker: SessionTracker,
) {

    private var newTreeUuid: UUID? = null
    private var convergence: Convergence? = null
    var currentTree: Tree? = null
        private set

    suspend fun pinLocation(): Boolean {
        locationDataCapturer.turnOnTreeCaptureMode()
        locationDataCapturer.converge()

        newTreeUuid = locationDataCapturer.generatedTreeUuid
        return if (locationDataCapturer.isLocationCoordinateAvailable()) {
            convergence = locationDataCapturer.convergence()
            true
        } else {
            false
        }
    }

    fun setImage(imageFile: File) {
        val tree = Tree(
            treeUuid = newTreeUuid!!,
            sessionId = sessionTracker.currentSessionId!!,
            content = "",
            photoPath = imageFile.absolutePath,
            convergence?.longitudeConvergence?.mean ?: 0.0,
            convergence?.latitudeConvergence?.mean ?: 0.0
        )
        tree.addTreeAttribute(Tree.ABS_STEP_COUNT_KEY, (stepCounter.absoluteStepCount ?: 0).toString())
        tree.addTreeAttribute(Tree.DELTA_STEP_COUNT_KEY, stepCounter.deltaSteps.toString())
        deviceOrientation.rotationMatrixSnapshot?.let {
            tree.addTreeAttribute(
                Tree.ROTATION_MATRIX_KEY, it.joinToString(",")
            )
        }
        currentTree = tree

        stepCounter.snapshotAbsoluteStepCountOnTreeCapture()
    }

    fun addAttribute(key: String, value: String) {
        currentTree?.addTreeAttribute(key, value)
    }

    fun setNote(note: String) {
        currentTree = currentTree?.copy(content = note)
    }

    suspend fun saveTree() {
        currentTree?.let {
            createTreeUseCase.execute(it)
        }
    }
}