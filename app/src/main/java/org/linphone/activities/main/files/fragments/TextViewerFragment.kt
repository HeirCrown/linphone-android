/*
 * Copyright (c) 2010-2021 Belledonne Communications SARL.
 *
 * This file is part of linphone-android
 * (see https://www.linphone.org).
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.linphone.activities.main.files.fragments

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import org.linphone.R
import org.linphone.activities.main.files.viewmodels.TextFileViewModel
import org.linphone.activities.main.files.viewmodels.TextFileViewModelFactory
import org.linphone.activities.main.fragments.SecureFragment
import org.linphone.activities.main.viewmodels.SharedMainViewModel
import org.linphone.databinding.FileTextViewerFragmentBinding

class TextViewerFragment : SecureFragment<FileTextViewerFragmentBinding>() {
    private lateinit var viewModel: TextFileViewModel
    private lateinit var sharedViewModel: SharedMainViewModel

    override fun getLayoutId(): Int = R.layout.file_text_viewer_fragment

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.lifecycleOwner = this

        sharedViewModel = requireActivity().run {
            ViewModelProvider(this).get(SharedMainViewModel::class.java)
        }

        val content = sharedViewModel.contentToOpen.value
        content ?: return

        (childFragmentManager.findFragmentById(R.id.top_bar_fragment) as? TopBarFragment)
            ?.setContent(content)

        viewModel = ViewModelProvider(
            this,
            TextFileViewModelFactory(content)
        )[TextFileViewModel::class.java]
        binding.viewModel = viewModel

        isSecure = arguments?.getBoolean("Secure") ?: false
    }
}