package il.co.averager.ui.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import il.co.averager.ui.ItemAdapter
import il.co.averager.R
import il.co.averager.data.model.Item
import il.co.averager.databinding.AllItemsLayoutBinding
import il.co.averager.ui.ItemsViewModel
import il.co.averager.utils.autoCleared

class AllItemsFragment : Fragment(R.layout.all_items_layout) {

    private var binding: AllItemsLayoutBinding by autoCleared()

    private val viewModel: ItemsViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = AllItemsLayoutBinding.inflate(layoutInflater)


        binding.btnAddNewSubject.setOnClickListener {
            findNavController().navigate(R.id.action_allItemsFragment_to_addItemFragment)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel.items?.observe(viewLifecycleOwner){

            binding.recycler.adapter =
                ItemAdapter(it, object : ItemAdapter.ItemListener {
                    override fun onItemClicked(index: Int) {
                        Toast.makeText(
                            requireContext(),
                            "${it[index]}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                        override fun onItemLongClicked(index: Int) {
//                        Item.ItemManager.remove(index)
//                        binding.recycler.adapter!!.notifyItemRemoved(index)
                        Toast.makeText(
                            requireContext(),
                            "${it[index]}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                })
            binding.recycler.layoutManager = LinearLayoutManager(requireContext())

            binding.tvAvarge.text = viewModel.sumAll().toString()
        }

        ItemTouchHelper(object : ItemTouchHelper.Callback() {
            override fun getMovementFlags(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder
            ) = makeFlag(
                ItemTouchHelper.ACTION_STATE_SWIPE,
                ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
            )

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                TODO("Not yet implemented")
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

                val item = (binding.recycler.adapter as ItemAdapter).itemAt(viewHolder.adapterPosition)
                viewModel.deleteItem(item)
                // Item.ItemManager.remove(viewHolder.adapterPosition)
                // binding.recycler.adapter!!.notifyItemRemoved(viewHolder.adapterPosition)
            }
        }).attachToRecyclerView(binding.recycler)
    }

}