package my.aolika.a7minutesworkout.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import my.aolika.a7minutesworkout.R
import my.aolika.a7minutesworkout.databinding.ItemExerciseBinding
import my.aolika.a7minutesworkout.model.ExerciseModel

class ExerciseRecyclerAdapter(val items: ArrayList<ExerciseModel>) :
    RecyclerView.Adapter<ExerciseRecyclerAdapter.ExerciseViewHolder>() {

    class ExerciseViewHolder(item: ItemExerciseBinding) : RecyclerView.ViewHolder(item.root) {
        val tvItem = item.tvItem

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseViewHolder {
        return ExerciseViewHolder(
            ItemExerciseBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ExerciseViewHolder, position: Int) {
        val model: ExerciseModel = items[position]
        holder.tvItem.text = model.getId().toString()

        when {
            model.getIsSelected() -> {
                holder.tvItem.background =
                    ContextCompat.getDrawable(
                        holder.tvItem.context,
                        R.drawable.item_circular_color_orange_border
                    )
                holder.tvItem.setTextColor(Color.parseColor("#FF4500"))
            }

            model.getIsCompleted() -> {

                holder.tvItem.background =
                    ContextCompat.getDrawable(
                        holder.tvItem.context,
                        R.drawable.item_circular_color_accent_background
                    )
                holder.tvItem.setTextColor(Color.parseColor("#212121"))

            }
            else -> {
                holder.tvItem.background =
                    ContextCompat.getDrawable(
                        holder.tvItem.context,
                        R.drawable.item_circular_color_grey_background
                    )
                holder.tvItem.setTextColor(Color.parseColor("#FFFFFF"))
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}