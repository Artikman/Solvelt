package com.example.listofspecialties.presentation.screeen.worker.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.listofspecialties.R
import com.example.listofspecialties.databinding.AdapterWorkerDescribeBinding
import com.example.listofspecialties.presentation.model.Worker
import com.example.listofspecialties.presentation.screeen.worker.viewmodel.WorkerViewModel
import java.util.ArrayList

internal class WorkersAdapter(val mListener: OnWorkersAdapterListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val workers: MutableList<Worker> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val holderWorkerBinding = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(parent.context),
            R.layout.adapter_worker_describe, parent,
            false
        )
        return WorkersViewHolder(holderWorkerBinding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as WorkersViewHolder).onBind(getItem(position))
    }

    private fun getItem(position: Int): Worker {
        return workers[position]
    }

    override fun getItemCount(): Int {
        return workers.size
    }

    fun addData(list: List<Worker>) {
        this.workers.clear()
        this.workers.addAll(list)
        notifyDataSetChanged()
    }

    inner class WorkersViewHolder(
        private val dataBinding: ViewDataBinding
    ) : RecyclerView.ViewHolder(dataBinding.root) {

        fun onBind(worker: Worker) {
            val holderWorkerBinding = dataBinding as AdapterWorkerDescribeBinding
            val workersViewModel =
                WorkerViewModel(
                    worker
                )
            holderWorkerBinding.viewModel = workersViewModel

            itemView.setOnClickListener {
                mListener.showWorkerDetails(worker)
            }
        }
    }
}