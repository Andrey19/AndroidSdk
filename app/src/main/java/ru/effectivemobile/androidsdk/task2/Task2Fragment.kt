package ru.effectivemobile.androidsdk.task2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.work.*
import ru.effectivemobile.androidsdk.R
import java.util.concurrent.TimeUnit

class Task2Fragment : Fragment() {

    private lateinit var tvStatus: TextView
    private lateinit var btnSchedule: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_task2, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvStatus = view.findViewById(R.id.tvStatus)
        btnSchedule = view.findViewById(R.id.btnSchedule)

        btnSchedule.setOnClickListener {
            scheduleChargingWorker()
        }

        checkExistingWork()
    }

    private fun scheduleChargingWorker() {
        val constraints = Constraints.Builder()
            .setRequiresCharging(true)
            .build()

        val workRequest = OneTimeWorkRequestBuilder<ChargingWorker>()
            .setConstraints(constraints)
            .setInitialDelay(5, TimeUnit.SECONDS)
            .build()

        WorkManager.getInstance(requireContext())
            .enqueueUniqueWork(
                "charging_work_unique",
                ExistingWorkPolicy.KEEP,
                workRequest
            )

        tvStatus.text = "✅ WorkManager запланирован (один раз)\n⚡ Будет выполнен при зарядке устройства"
        btnSchedule.isEnabled = false
    }

    private fun checkExistingWork() {
        WorkManager.getInstance(requireContext())
            .getWorkInfosForUniqueWorkLiveData("charging_work_unique")
            .observe(viewLifecycleOwner) { workInfos ->
                if (workInfos.isNullOrEmpty()) {
                    btnSchedule.isEnabled = true
                    tvStatus.text = "⏳ WorkManager не запланирован"
                } else {
                    val state = workInfos[0].state
                    when {
                        state.isFinished -> {
                            tvStatus.text = "✅ WorkManager выполнен\n📢 Уведомление было отправлено"
                            btnSchedule.isEnabled = false
                        }
                        state == WorkInfo.State.ENQUEUED -> {
                            tvStatus.text = "⏳ WorkManager запланирован\n🔌 Ожидает подключения зарядки"
                            btnSchedule.isEnabled = false
                        }
                        state == WorkInfo.State.RUNNING -> {
                            tvStatus.text = "🔄 WorkManager выполняется..."
                            btnSchedule.isEnabled = false
                        }
                        else -> {
                            btnSchedule.isEnabled = true
                        }
                    }
                }
            }
    }
}