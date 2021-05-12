package com.example.listofspecialties.presentation.screeen.workersspeciality.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.listofspecialties.R
import com.example.listofspecialties.databinding.AdapterWorkerSpecialityDescribeBinding
import com.example.listofspecialties.presentation.model.Speciality
import com.example.listofspecialties.presentation.screeen.workersspeciality.viewmodel.WorkerSpecialityViewModel
import java.util.ArrayList

internal class WorkersSpecialityAdapter(val mListener: OnWorkersSpecialityAdapterListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val speciality: MutableList<Speciality> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val holderWorkersSpecialityBinding = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(parent.context),
            R.layout.adapter_worker_speciality_describe, parent,
            false
        )
        return WorkersSpecialityViewHolder(holderWorkersSpecialityBinding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as WorkersSpecialityViewHolder).onBind(getItem(position))
    }

    private fun getItem(position: Int): Speciality {
        return speciality[position]
    }

    override fun getItemCount(): Int {
        return speciality.size
    }

    fun addData(list: List<Speciality>) {
        this.speciality.clear()
        this.speciality.addAll(list)
        notifyDataSetChanged()
    }

    inner class WorkersSpecialityViewHolder(
        private val dataBinding: ViewDataBinding
    ) : RecyclerView.ViewHolder(dataBinding.root) {

        fun onBind(speciality: Speciality) {
            val holderWorkerBinding = dataBinding as AdapterWorkerSpecialityDescribeBinding
            val workerSpecialityViewModel =
                WorkerSpecialityViewModel(
                    speciality
                )
            holderWorkerBinding.viewModel = workerSpecialityViewModel

            itemView.setOnClickListener {
                mListener.showWorkers(speciality.specialityId ?: 0)
            }
        }
    }
}