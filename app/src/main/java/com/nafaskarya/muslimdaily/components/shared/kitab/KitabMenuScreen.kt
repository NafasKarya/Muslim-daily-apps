package com.nafaskarya.muslimdaily.ui.kitab

import androidx.annotation.DrawableRes
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nafaskarya.muslimdaily.R

// --- Data Models and Dummy Data (tidak berubah) ---
data class Book(val title: String, val author: String? = null, @DrawableRes val imageRes: Int)
data class Category(val name: String, @DrawableRes val imageRes: Int)

val featuredBooks = listOf(
    Book("Kisah Klasik Suku Waq Waq", "Al-Qazwini", R.drawable.img_fajr),
    Book("Perjalanan Ibnu Batutah", "Ibnu Batutah", R.drawable.img_fajr),
    Book("Hayy bin Yaqdhan", "Ibnu Tufail", R.drawable.img_fajr)
)
val alQazwiniBooks = listOf(
    Book("Ajaib al-Makhluqat", imageRes = R.drawable.img_fajr),
    Book("Ghara'ib al-Mawjudat", imageRes = R.drawable.img_fajr),
    Book("Athar al-Bilad", imageRes = R.drawable.img_fajr),
    Book("Akhbar al-Buldan", imageRes = R.drawable.img_fajr)
)
val alJahizBooks = alQazwiniBooks.shuffled()
val categories = listOf(
    Category("Hewan Aneh", R.drawable.img_fajr),
    Category("Geografi", R.drawable.img_fajr),
    Category("Filsafat", R.drawable.img_fajr),
    Category("Sejarah", R.drawable.img_fajr),
    Category("Astronomi", R.drawable.img_fajr),
    Category("Sastra", R.drawable.img_fajr)
)


@Composable
fun KitabMenuScreen() {
    // Box paling luar dengan background putih
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Column untuk konten yang bisa di-scroll
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            // Banner section
            FeaturedPagerSection(books = featuredBooks)

            // Sisa konten dengan background putih
            Column(Modifier.background(Color.White)) {
                Spacer(modifier = Modifier.height(24.dp))
                BookSection(title = "Kitab Klasik Al-Qazwini", books = alQazwiniBooks)
                Spacer(modifier = Modifier.height(24.dp))
                BookSection(title = "Kitab Klasik Al-Jahiz", books = alJahizBooks)
                Spacer(modifier = Modifier.height(24.dp))
                CategorySection(title = "Kategori Kitab Klasik", categories = categories)
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FeaturedPagerSection(books: List<Book>) {
    Spacer(modifier = Modifier.windowInsetsTopHeight(WindowInsets.statusBars))
    Spacer(modifier = Modifier.height(16.dp))

    val pagerState = rememberPagerState(pageCount = { books.size })
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.background(Color.White)
    ) {
        Text(
            text = "Paling Seru Bulan ini",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )
        Spacer(modifier = Modifier.height(12.dp))
        HorizontalPager(
            state = pagerState,
            contentPadding = PaddingValues(horizontal = 16.dp),
            pageSpacing = 12.dp
        ) { page ->
            val book = books[page]
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Box {
                    Image(
                        painter = painterResource(id = book.imageRes),
                        contentDescription = book.title,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                brush = Brush.verticalGradient(
                                    colors = listOf(
                                        Color.Transparent,
                                        Color.Black.copy(alpha = 0.7f)
                                    ),
                                    startY = 300f
                                )
                            )
                    )
                    Column(
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .padding(16.dp)
                    ) {
                        Text(
                            text = book.title,
                            color = Color.White,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                        book.author?.let {
                            Text(
                                text = it,
                                color = Color.White.copy(alpha = 0.8f),
                                fontSize = 14.sp
                            )
                        }
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(12.dp))
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            repeat(pagerState.pageCount) { iteration ->
                val color = if (pagerState.currentPage == iteration)
                    Color.White
                else
                    Color.Gray.copy(alpha = 0.5f)
                Box(
                    modifier = Modifier
                        .padding(2.dp)
                        .clip(CircleShape)
                        .background(color)
                        .size(8.dp)
                )
            }
        }
    }
}

@Composable
fun BookSection(title: String, books: List<Book>) {
    Column {
        SectionHeader(title = title, onSeeAllClick = { /* TODO */ })
        Spacer(modifier = Modifier.height(8.dp))
        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(books) { book -> BookCard(book = book) }
        }
    }
}

@Composable
fun CategorySection(title: String, categories: List<Category>) {
    Column {
        SectionHeader(title = title, onSeeAllClick = { /* TODO */ })
        Spacer(modifier = Modifier.height(8.dp))
        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(categories) { category -> CategoryCircle(category = category) }
        }
    }
}

@Composable
fun SectionHeader(title: String, onSeeAllClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "Lihat semua",
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.SemiBold
        )
    }
}

@Composable
fun BookCard(book: Book) {
    Column(
        modifier = Modifier.width(130.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            shape = RoundedCornerShape(12.dp),
            elevation = CardDefaults.cardElevation(2.dp)
        ) {
            Image(
                painter = painterResource(id = book.imageRes),
                contentDescription = book.title,
                modifier = Modifier
                    .width(130.dp)
                    .height(180.dp),
                contentScale = ContentScale.Crop
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = book.title,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.SemiBold,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun CategoryCircle(category: Category) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.width(70.dp)
    ) {
        Image(
            painter = painterResource(id = category.imageRes),
            contentDescription = category.name,
            modifier = Modifier
                .size(70.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = category.name,
            style = MaterialTheme.typography.bodySmall,
            textAlign = TextAlign.Center
        )
    }
}


@Preview(showBackground = true, device = "id:pixel_6")
@Composable
fun KitabMenuScreenPreview() {
    MaterialTheme {
        KitabMenuScreen()
    }
}