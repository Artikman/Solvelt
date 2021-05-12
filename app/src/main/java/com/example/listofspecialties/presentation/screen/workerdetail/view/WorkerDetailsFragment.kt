package com.example.listofspecialties.presentation.screeen.workerdetail.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.listofspecialties.MainActivity
import com.example.listofspecialties.R
import com.example.listofspecialties.databinding.FragmentWorkerDetailsBinding
import com.example.listofspecialties.presentation.screeen.workerdetail.model.WorkerDetailsModel
import com.example.listofspecialties.presentation.screeen.workerdetail.viewmodel.WorkerDetailsViewModel
import com.example.listofspecialties.util.loadImageFull
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WorkerDetailsFragment : Fragment() {

    private lateinit var fragmentWorkerDetailsBinding: FragmentWorkerDetailsBinding
    private val viewModel: WorkerDetailsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentWorkerDetailsBinding = DataBindingUtil
            .inflate(inflater, R.layout.fragment_worker_details, container, false)

        (activity as MainActivity).setSupportActionBar(fragmentWorkerDetailsBinding.detailToolbar)
        (activity as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (activity as MainActivity).supportActionBar?.setDisplayShowHomeEnabled(true)

        fragmentWorkerDetailsBinding.viewModel = viewModel

        val workerDetails =
            arguments?.getParcelable<WorkerDetailsModel>(
                KEY_WORKER_DETAILS_ID
            )
                ?: return fragmentWorkerDetailsBinding.root

        viewModel.setDetail(workerDetails)
        viewModel.checkFavoriteStatus(workerDetails)

        viewModel.workerFavoriteData.observe(viewLifecycleOwner, Observer {
            fragmentWorkerDetailsBinding.actvFirstName.text = it?.firstName
            fragmentWorkerDetailsBinding.actvLastName.text = it?.lastName
            fragmentWorkerDetailsBinding.actvBirthday.text = it?.birthday
            fragmentWorkerDetailsBinding.actvAge.text = it?.age
            fragmentWorkerDetailsBinding.actvSpecialityName.text = it?.specialityName

            if (it?.avatarUrl == null || it.avatarUrl?.isEmpty() == true) {
                fragmentWorkerDetailsBinding
                    .detailToolbarImageView
                    .setImageResource(R.drawable.ic_launcher_background)
            } else {
                fragmentWorkerDetailsBinding.detailToolbarImageView.loadImageFull(it.avatarUrl)
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

        return fragmentWorkerDetailsBinding.root
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> parentFragmentManager.popBackStackImmediate()
            else -> super.onOptionsItemSelected(item)
        }
    }

    companion object {
        private const val KEY_WORKER_DETAILS_ID = "KEY_WORKER_DETAILS_ID"
        val FRAGMENT_NAME: String = WorkerDetailsFragment::class.java.name

        @JvmStatic
        fun newInstance(workerDetailsModel: WorkerDetailsModel) =
            WorkerDetailsFragment()
                .apply {
                    arguments = Bundle().apply {
                        putParcelable(KEY_WORKER_DETAILS_ID, workerDetailsModel)
                    }
                }
    }
}