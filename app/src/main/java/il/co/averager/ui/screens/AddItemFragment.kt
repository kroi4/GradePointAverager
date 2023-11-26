package il.co.averager.ui.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import il.co.averager.R
import il.co.averager.data.model.Item
import il.co.averager.databinding.AddItemLayoutBinding
import il.co.averager.ui.ItemsViewModel
import il.co.averager.utils.autoCleared

class AddItemFragment : Fragment(R.layout.add_item_layout) {

    private var binding: AddItemLayoutBinding by autoCleared()

    private val viewModel: ItemsViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = AddItemLayoutBinding.inflate(layoutInflater)


        binding.apply {
            btnFinish.setOnClickListener {
                val item = Item(
                    tilSubject.text.toString(),
                    tilGrade.text.toString().toInt(),
                    tilPoints.text.toString().toDouble()
                )
                //Item.ItemManager.add(item)

                viewModel.addItem(item)

                findNavController().navigate(R.id.action_addItemFragment_to_allItemsFragment)
            }
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}