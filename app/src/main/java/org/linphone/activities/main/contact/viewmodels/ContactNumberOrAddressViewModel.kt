/*
 * Copyright (c) 2010-2020 Belledonne Communications SARL.
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
package org.linphone.activities.main.contact.viewmodels

import androidx.lifecycle.ViewModel
import org.linphone.core.Address

class ContactNumberOrAddressViewModel(
    val address: Address,
    val hasPresence: Boolean,
    val displayedValue: String,
    val isSip: Boolean = true,
    val showSecureChat: Boolean = false,
    private val listener: ContactNumberOrAddressClickListener
) : ViewModel() {
    val showInvite = !hasPresence && !isSip

    fun startCall() {
        listener.onCall(address)
    }

    fun startChat(secured: Boolean) {
        listener.onChat(address, secured)
    }

    fun smsInvite() {
        listener.onSmsInvite(displayedValue)
    }
}

interface ContactNumberOrAddressClickListener {
    fun onCall(address: Address)

    fun onChat(address: Address, isSecured: Boolean)

    fun onSmsInvite(number: String)
}