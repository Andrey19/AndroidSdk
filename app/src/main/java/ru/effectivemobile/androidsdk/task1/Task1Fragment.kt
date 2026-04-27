package ru.effectivemobile.androidsdk.task1

import ru.effectivemobile.androidsdk.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class Task1Fragment : Fragment() {

    private lateinit var router: FragmentRouter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_task1, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        router = FragmentRouter(childFragmentManager, R.id.subFragmentContainer)
    }

    fun getRouter(): Router = router
}