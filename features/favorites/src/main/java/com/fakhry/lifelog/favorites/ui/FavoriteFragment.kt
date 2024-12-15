package com.fakhry.lifelog.favorites.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.fakhry.lifelog.storage.model.DateNoteEntity
import com.fakhry.lifelog.storage.model.NoteEntity
import com.fakhry.lifelog.components.adapters.ListDateWithNoteAdapter
import com.fakhry.lifelog.favorites.databinding.FragmentFavoriteBinding

class FavoriteFragment : Fragment() {

    private lateinit var favoriteViewModel: FavoriteViewModel
    private lateinit var binding: FragmentFavoriteBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val factory = context?.let { FavoriteViewModel.provideFactory(it) } ?: return
        favoriteViewModel = ViewModelProvider(this, factory)[FavoriteViewModel::class.java]
        favoriteViewModel.getFavoriteNote().observe(viewLifecycleOwner) { listNote ->
            println("Trace ui currentThread ${Thread.currentThread().name}")
            if (!listNote.isNullOrEmpty()) {
                binding.ivEmptyDashboard.visibility = View.GONE
                binding.tvEmptyDashboard.visibility = View.GONE
                binding.btnGoToDashboard.visibility = View.GONE
                binding.rvFavorite.visibility = View.VISIBLE

                createFavData(listNote)
            } else {
                binding.ivEmptyDashboard.visibility = View.VISIBLE
                binding.tvEmptyDashboard.visibility = View.VISIBLE
                binding.btnGoToDashboard.visibility = View.VISIBLE
                binding.rvFavorite.visibility = View.GONE
            }

        }

        binding.btnGoToDashboard.setOnClickListener {
            activity?.onBackPressedDispatcher?.onBackPressed()
        }
    }

    private fun createFavData(listFavNote: List<NoteEntity>) {
        val listDate = ArrayList<String>()
        listFavNote.forEach {
            listDate.add(it.createdDate)
        }
        val listUniqueDate = listDate.distinct()
        val listDateNoteEntity = ArrayList<DateNoteEntity>()

        for (date in listUniqueDate) {
            val listDateBasedNote = ArrayList<NoteEntity>()
            for (note in listFavNote) {
                if (date == note.createdDate) {
                    listDateBasedNote.add(note)
                }
            }
            val dateNote = DateNoteEntity(date, listDateBasedNote)
            listDateNoteEntity.add(dateNote)
        }
        setRecyclerViewFavNote(listDateNoteEntity)
    }

    private fun setRecyclerViewFavNote(data: ArrayList<DateNoteEntity>) {
        binding.rvFavorite.setHasFixedSize(true)
        val parentNoteAdapter = ListDateWithNoteAdapter()
        parentNoteAdapter.notifyDataSetChanged()
        parentNoteAdapter.setData(data)

        binding.rvFavorite.layoutManager =
            LinearLayoutManager(binding.rvFavorite.context, LinearLayoutManager.VERTICAL, false)
        binding.rvFavorite.adapter = parentNoteAdapter
    }
}