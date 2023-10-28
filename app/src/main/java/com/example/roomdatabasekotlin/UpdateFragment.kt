package com.example.roomdatabasekotlin

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.roomdatabasekotlin.databinding.FragmentUpdateBinding
import java.lang.Integer.parseInt


class UpdateFragment : Fragment() {
    private var _binding: FragmentUpdateBinding? = null
    private val binding get() = _binding!!
    private val args by navArgs<UpdateFragmentArgs>()
    private lateinit var mUserViewModel: UserViewModel



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUpdateBinding.inflate(inflater, container, false)
        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        binding.updateName.setText(args.currentUser.name)
        binding.updateCouse.setText(args.currentUser.course)
        binding.updateDob.setText(args.currentUser.dob.toString())


        binding.updateEntry.setText(args.currentUser.entrydate.toString())
        binding.updateFather.setText(args.currentUser.fathername)
       binding.updateBtn.setOnClickListener{
           updateItem()
       }

//

            setHasOptionsMenu(true)
      return binding.root
    }




    private fun updateItem() {
        val name=binding.updateName.text.toString()
        val course=binding.updateCouse.text.toString()
        val fathername=binding.updateFather.text.toString()
        val dob=   Integer.parseInt(binding.updateDob.text.toString())

        val entrydate= Integer.parseInt(binding.updateEntry.text.toString())

        if (inputCheck(name, course, fathername)) {
            val updateUser = User(
            args.currentUser.reg_no,

                name,
                course,
                dob,
                fathername,
                entrydate)

            mUserViewModel.updateUser(updateUser)
        Toast.makeText(requireContext(), "Updated Successfully !", Toast.LENGTH_SHORT).show()

        findNavController().navigate(R.id.action_updateFragment_to_listFragment)
    } else {
        Toast.makeText(requireContext(), "Please fill all fields !", Toast.LENGTH_SHORT).show()
    }

}

    private fun inputCheck(name: String, course: String, fathername: String): Boolean {
        return !(TextUtils.isEmpty(name) && TextUtils.isEmpty(fathername) && TextUtils.isEmpty(course))

    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
    inflater.inflate(R.menu.delete_menu, menu)
}

override fun onOptionsItemSelected(item: MenuItem): Boolean {
    if (item.itemId == R.id.menu_delete) {
        deleteUser()
    }
    return super.onOptionsItemSelected(item)
}

private fun deleteUser() {
    val builder = AlertDialog.Builder(requireContext())
    builder.setPositiveButton("Yes") { _, _ ->
        mUserViewModel.deleteUser(args.currentUser)
        Toast.makeText(
            requireContext(),
            "Successfully removed ${args.currentUser.name}",
            Toast.LENGTH_SHORT)
            .show()

        findNavController().navigate(R.id.action_updateFragment_to_listFragment)
    }
    builder.setNegativeButton("No") { _, _ -> }
    builder.setTitle("Delete ${args.currentUser.name} ?")
    builder.setMessage("Are you sure to remove ${args.currentUser.name} ?")
    builder.create().show()
}

override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
    }


}

