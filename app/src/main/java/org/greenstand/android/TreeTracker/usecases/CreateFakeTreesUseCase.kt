package org.greenstand.android.TreeTracker.usecases

import android.content.Context
import java.util.UUID
import org.greenstand.android.TreeTracker.models.LocationUpdateManager
import org.greenstand.android.TreeTracker.models.Tree
import org.greenstand.android.TreeTracker.models.Users
import org.greenstand.android.TreeTracker.utilities.ImageUtils

data class CreateFakeTreesParams(val amount: Int)

class CreateFakeTreesUseCase(
    private val locationUpdateManager: LocationUpdateManager,
    private val users: Users,
    private val context: Context,
    private val createTreeUseCase: CreateTreeUseCase
) : UseCase<CreateFakeTreesParams, Unit>() {

    override suspend fun execute(params: CreateFakeTreesParams) {
        for (i in 0..params.amount) {

            val file = ImageUtils.createTestImageFile(context)

            val tree = Tree(
                planterCheckInId = users.currentSessionUser!!.id,
                photoPath = file.absolutePath,
                content = "My Note",
                treeUuid = UUID.randomUUID(),
                meanLongitude = 0.0,
                meanLatitude = 0.0
            )

            createTreeUseCase.execute(tree)
        }
    }
}
