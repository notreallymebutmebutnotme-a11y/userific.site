package com.onefocus.app.home

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import com.onefocus.app.R
import com.onefocus.app.databinding.GridItemTaskBinding

class HomeTaskAdapter(private var tasks: List<Task>) :
    RecyclerView.Adapter<HomeTaskAdapter.TaskViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val binding =
            GridItemTaskBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TaskViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(tasks[position])
        if (position == 0) {
            holder.itemView.setOnClickListener {
                val context = holder.itemView.context
                val builder = AlertDialog.Builder(context)
                builder.setTitle("Custom Task")
                val input = EditText(context)
                builder.setView(input)
                builder.setPositiveButton("OK") { _, _ ->
                    val taskName = input.text.toString()
                    val newTasks = tasks.toMutableList()
                    newTasks.add(Task(taskName, R.drawable.ic_custom_task))
                    updateTasks(newTasks)
                }
                builder.setNegativeButton("Cancel") { dialog, _ ->
                    dialog.cancel()
                }
                builder.show()
            }
        }
    }

    override fun getItemCount(): Int = tasks.size

    fun updateTasks(tasks: List<Task>) {
        this.tasks = tasks
        notifyDataSetChanged()
    }

    inner class TaskViewHolder(private val binding: GridItemTaskBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(task: Task) {
            binding.taskIcon.setImageResource(task.icon)
            binding.taskName.text = task.name
        }
    }
}
