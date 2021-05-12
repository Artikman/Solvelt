package com.example.listofspecialties.presentation.screeen.workersspeciality.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.listofspecialties.MainActivity
import com.example.listofspecialties.R
import com.example.listofspecialties.databinding.FragmentWorkerSpecialityBinding
import com.example.listofspecialties.presentation.model.Speciality
import com.example.listofspecialties.presentation.screeen.worker.view.WorkersFragment
import com.example.listofspecialties.presentation.screeen.workersspeciality.adapter.OnWorkersSpecialityAdapterListener
import com.example.listofspecialties.presentation.screeen.workersspeciality.adapter.WorkersSpecialityAdapter
import com.example.listofspecialties.presentation.screeen.workersspeciality.viewmodel.WorkersSpecialityViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WorkersSpecialityFragment : Fragment(), OnWorkersSpecialityAdapterListener {

    private lateinit var fragmentWorkersSpecialityBinding: FragmentWorkerSpecialityBinding
    private var adapter: WorkersSpecialityAdapter? = null

    private val viewModel: WorkersSpecialityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter =
            WorkersSpecialityAdapter(
                this
            )
        viewModel.loadWorkers()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentWorkersSpecialityBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_worker_speciality,
            container,
            false
        )

        (activity as MainActivity).setSupportActionBar(fragmentWorkersSpecialityBinding.toolbar)

        fragmentWorkersSpecialityBinding.viewModel = viewModel
        fragmentWorkersSpecialityBinding.workersRecyclerView.adapter = adapter

        viewModel.isLoad.observe(viewLifecycleOwner, Observer {
            it?.let { visibility ->
                fragmentWorkersSpecialityBinding.workersSpecialityProgressBar.visibility =
                    if (visibility) View.GONE else View.VISIBLE
            }
        })

        viewModel.specialityReceivedLiveData.observe(viewLifecycleOwner, Observer {
            it?.let {
                initRecyclerView(it)
            }
        })

        viewModel.isError.observe(viewLifecycleOwner, Observer {
            it?.let { isError ->
                if (isError) {
                    Toast
                        .makeText(requireContext(), R.string.ERROR_MESSAGE, Toast.LENGTH_SHORT)
                        .show()
                }
            }
        })

        return fragmentWorkersSpecialityBinding.root
    }

    private fun initRecyclerView(speciality: List<Speciality>) {
        adapter?.addData(speciality)
    }

    override fun showWorkers(specialityId: Int) {
        parentFragmentManager.beginTransaction()
            .replace(
                R.id.fragment_container,
                WorkersFragment.newInstance(specialityId),
                WorkersFragment.FRAGMENT_NAME
            )
            .addToBackStack(WorkersFragment.FRAGMENT_NAME)
            .commitAllowingStateLoss()
    }

    override fun onDetach() {
        super.onDetach()
        adapter = null
    }

    companion object {
        val FRAGMENT_NAME: String = WorkersSpecialityFragment::class.java.name

        @JvmStatic
        fun newInstance() =
            WorkersSpecialityFragment()
                .apply {
                    arguments = Bundle().apply {
                    }
                }
    }
}