package com.nafaskarya.muslimdaily.components.shared

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nafaskarya.muslimdaily.components.widgets.CustomSearchBar
import com.nafaskarya.muslimdaily.components.widgets.LastSearchYou
import com.nafaskarya.muslimdaily.components.widgets.PopularCard
import com.nafaskarya.muslimdaily.components.widgets.HijrahCard
import com.nafaskarya.muslimdaily.layouts.theme.AppImages

@Composable
fun SearchPage() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFE8F5E9))
    ) {
        Spacer(modifier = Modifier.height(60.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        ) {
            CustomSearchBar()
        }

        Spacer(modifier = Modifier.height(30.dp))

        // ✅ Box dengan radius kiri atas doang
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .clip(RoundedCornerShape(topStart = 62.dp))
                .background(Color.White)
        ) {
            // 🔑 Biar semua konten bisa discroll 1 page
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(start = 0.dp, end = 0.dp)
            ) {
                Spacer(modifier = Modifier.height(60.dp))

                // Section: Pencarian Terakhir
                Text(
                    text = "Pencarian Terakhir",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    ),
                    color = Color.Black,
                    modifier = Modifier.padding(start = 32.dp, bottom = 16.dp)
                )

                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    contentPadding = PaddingValues(horizontal = 20.dp)
                ) {
                    items(5) {
                        LastSearchYou(
                            title = "Golden Gate Bridge",
                            subtitle = "San Francisco, California, USA",
                            modifier = Modifier
                                .width(260.dp)
                                .height(180.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(40.dp))

                // Section: Suka Dicari Taubaters
                Text(
                    text = "Suka Dicari Taubaters",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    ),
                    color = Color.Black,
                    modifier = Modifier.padding(start = 32.dp, bottom = 16.dp)
                )

                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    contentPadding = PaddingValues(horizontal = 20.dp)
                ) {
                    items(5) {
                        PopularCard(
                            title = "Jaga Lisan – Ust. Rafiq",
                            imageRes = AppImages.PrayerTime
                        )
                    }
                }

                Spacer(modifier = Modifier.height(40.dp))

                // Section: Berani Hijrah Sob
                Text(
                    text = "Berani Hijrah Sob",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    ),
                    color = Color.Black,
                    modifier = Modifier.padding(start = 32.dp, bottom = 16.dp)
                )

                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    contentPadding = PaddingValues(horizontal = 20.dp)
                ) {
                    items(5) {
                        HijrahCard(
                            text = "# HijrahSebelumTerlambat",
                            imageRes = AppImages.PrayerTime
                        )
                    }
                }

                Spacer(modifier = Modifier.height(60.dp))
            }
        }
    }
}
