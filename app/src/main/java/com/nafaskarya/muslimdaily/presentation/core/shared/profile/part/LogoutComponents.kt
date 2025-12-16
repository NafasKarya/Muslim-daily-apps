package com.nafaskarya.muslimdaily.presentation.core.shared.profile.part

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.nafaskarya.muslimdaily.presentation.core.constant.ProfileRedDanger
import com.nafaskarya.muslimdaily.presentation.core.constant.ProfileSurface
import com.nafaskarya.muslimdaily.presentation.core.constant.ProfileText
import com.nafaskarya.muslimdaily.presentation.core.constant.TextWhite
import com.nafaskarya.muslimdaily.presentation.core.utils.windows.WindowDimensions

@Composable
fun LogoutButton(
    dimen: WindowDimensions,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = ProfileSurface,
            contentColor = ProfileRedDanger
        ),
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        contentPadding = PaddingValues(0.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.Logout,
                contentDescription = null,
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = ProfileText.BTN_LOGOUT,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold
                )
            )
        }
    }
}

@Composable
fun LogoutConfirmationDialog(
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        containerColor = ProfileSurface,
        title = {
            Text(
                text = ProfileText.LOGOUT_DIALOG_TITLE,
                color = TextWhite,
                fontWeight = FontWeight.Bold
            )
        },
        text = {
            Text(
                text = ProfileText.LOGOUT_DIALOG_MSG,
                color = TextWhite.copy(alpha = 0.8f)
            )
        },
        confirmButton = {
            Button(
                onClick = onConfirm,
                colors = ButtonDefaults.buttonColors(containerColor = ProfileRedDanger)
            ) {
                Text(ProfileText.BTN_LOGOUT, color = TextWhite, fontWeight = FontWeight.Bold)
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(ProfileText.BTN_CANCEL, color = TextWhite, fontWeight = FontWeight.Bold)
            }
        }
    )
}