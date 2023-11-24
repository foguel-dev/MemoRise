package com.example.memorise.ui.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.example.memorise.R
import com.example.memorise.databinding.LayoutAdapterBinding
import com.example.memorise.model.Note
import com.example.memorise.fragments.EditNoteFragment
import com.example.memorise.fragments.HomeFragment
import com.example.memorise.fragments.HomeFragmentDirections
import com.example.memorise.fragments.NoteFragment
import java.util.Random

class NoteAdapter: RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    class NoteViewHolder(val itemBinding: LayoutAdapterBinding) :
        RecyclerView.ViewHolder(itemBinding.root)

    private val differCallback =
        object : DiffUtil.ItemCallback<Note>() {
            override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
                return oldItem.id == newItem.id &&
                        oldItem.description == newItem.description &&
                        oldItem.title == newItem.title
            }

            override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
                return oldItem == newItem
            }

        }

    val differ = AsyncListDiffer(this, differCallback)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(
            LayoutAdapterBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }


    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val currentNote = differ.currentList[position]
        holder.itemBinding.tvTitle.text = currentNote.title
        holder.itemBinding.tvDescription.text = currentNote.description
        val random = Random()

        fun generatePastelColorWithBlackText(): Int {
            // Fijar el componente de alfa a 255 (totalmente opaco)
            val alpha = 255

            // Generar valores aleatorios para los componentes HSB (Tono, Saturación, Brillo)
            val hue = random.nextFloat() * 360
            val saturation = 0.5f + random.nextFloat() * 0.5f // Saturación en el rango 0.5 - 1.0
            val brightness = 0.6f + random.nextFloat() * 0.4f // Brillo en el rango 0.6 - 1.0

            // Convertir HSB a RGB
            val rgb = FloatArray(3)
            Color.colorToHSV(Color.BLACK, rgb)
            rgb[0] = hue
            rgb[1] = saturation
            rgb[2] = brightness

            // Convertir de nuevo a RGB
            val pastelColor = Color.HSVToColor(alpha, rgb)

            return pastelColor
        }

        val color = generatePastelColorWithBlackText()

        holder.itemBinding.cardView.setBackgroundColor(color)

        holder.itemView.setOnClickListener { view ->

            val direction = HomeFragmentDirections
                .actionHomeFragment2ToEditNoteFragment2(currentNote)
            view.findNavController().navigate(direction)
        }


    }


    override fun getItemCount(): Int {
        return differ.currentList.size
    }

}