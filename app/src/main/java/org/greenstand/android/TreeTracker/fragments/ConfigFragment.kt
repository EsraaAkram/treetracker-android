package org.greenstand.android.TreeTracker.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.greenstand.android.TreeTracker.viewmodels.ConfigViewModel
import org.koin.android.ext.android.inject

class ConfigFragment : Fragment() {

    private val configViewModel by inject<ConfigViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        TODO()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configViewModel
            .getLocationDataConfig()
            .observe(
                viewLifecycleOwner,
                { locationDataConfig ->
//                    bindings.minTimeBtwnUpdatesVal.setText(
//                        locationDataConfig.minTimeBetweenUpdates.toString()
//                    )
//                    bindings.minDisBtwnUpdatesVal.setText(
//                        locationDataConfig.minDistanceBetweenUpdates.toString()
//                    )
//                    bindings.convergenceTimeoutVal.setText(locationDataConfig.convergenceTimeout.toString())
//                    bindings.convergenceDataSizeVal.setText(
//                        locationDataConfig.convergenceDataSize.toString()
//                    )
//                    bindings.lonStdDevThresholdVal.setText(locationDataConfig.lonStdDevThreshold.toString())
//                    bindings.latStdDevThresholdVal.setText(locationDataConfig.latStdDevThreshold.toString())
                }
            )

//        bindings.saveConfigButton.setOnClickListener {
//            configViewModel.updateLocationDataConfig(
//                LocationDataConfig(
//                    minTimeBetweenUpdates = bindings.minTimeBtwnUpdatesVal.text.toString().toLong(),
//                    minDistanceBetweenUpdates = bindings.minDisBtwnUpdatesVal.text.toString().toFloat(),
//                    convergenceTimeout = bindings.convergenceTimeoutVal.text.toString().toLong(),
//                    convergenceDataSize = bindings.convergenceDataSizeVal.text.toString().toInt(),
//                    lonStdDevThreshold = bindings.lonStdDevThresholdVal.text.toString().toFloat(),
//                    latStdDevThreshold = bindings.latStdDevThresholdVal.text.toString().toFloat()
//                )
//            )
//            val toast = Toast.makeText(activity, "Configuration Saved !!", Toast.LENGTH_SHORT)
//            toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0)
//            toast.show()
//        }
    }
}
