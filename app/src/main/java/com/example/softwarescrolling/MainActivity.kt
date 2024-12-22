package com.example.softwarescrolling

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.softwarescrolling.ui.theme.SoftwareScrollingTheme
import kotlinx.coroutines.launch
import kotlin.collections.component1
import kotlin.collections.component2

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalFoundationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SoftwareScrollingTheme {
                val persons = mutableListOf(
                    PersonModel("Андрей", "Протасов", "Инжинер"),
                    PersonModel("Александр", "Доманов", "Программист"),
                    PersonModel("Анна", "Журкина", "Врач"),
                    PersonModel("Александра", "Кононова", "Учитель"),
                    PersonModel("Иван", "Петров", "Инжинер"),
                    PersonModel("Алексей", "Долотов", "Программист"),
                    PersonModel("Анна", "Ломакина", "Учитель"),
                    PersonModel("Андрей", "Липов", "Врач"),
                    PersonModel("Инга", "Самолетова", "Инжинер"),
                    PersonModel("Петр", "Семенов", "Программист"),
                    PersonModel("Антон", "Фирсов", "Учитель"),
                    PersonModel("Виталий", "Иванов", "Врач"),
                    PersonModel("Иван", "Сорокин", "Инжинер"),
                    PersonModel("Роман", "Ильин", "Программист"),
                    PersonModel("Илья", "Антонов", "Врач"),
                    PersonModel("Ирина", "Ефимова", "Учитель"),
                    PersonModel("Елизавета", "Рудакова", "Инжинер"),
                    PersonModel("Георгий", "Попов", "Программист"),
                    PersonModel("Татьяна", "Агеева", "Учитель"),
                    PersonModel("Тимофей", "Федоров", "Врач"),
                    PersonModel("Тимур", "Петров", "Инжинер"),
                    PersonModel("Савелий", "Виноградов", "Программист"),
                    PersonModel("Илья", "Семенов", "Учитель"),
                    PersonModel("Анастасия", "Филатова", "Врач")
                ).groupBy { it.role }.mapValues {
                    it.value.sortedBy { it.name }
                }
                Content(persons)
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Content(persons: Map<String, List<PersonModel>>) {
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    LazyColumn(
        state = listState
    ) {
        item {
            Text(
                text = "В конец",
                Modifier
                    .padding(8.dp)
                    .background(Color.DarkGray)
                    .padding(6.dp)
                    .clickable {
                        coroutineScope.launch {
                            listState.animateScrollToItem(23)
                        }
                    },
                fontSize = 28.sp,
                color = Color.White
            )
        }
        persons.forEach { (type, name) ->
            stickyHeader {
                Text(
                    text = type,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier
                        .background(Color.Blue)
                        .padding(6.dp)
                        .fillParentMaxWidth()
                )
            }
            items(name) { person ->
                Text(
                    text = "${person.name} ${person.secondName}",
                    Modifier.padding(6.dp),
                    fontSize = 30.sp
                )
            }
        }
        item {
            Text(
                text = "В начало",
                Modifier
                    .padding(8.dp)
                    .background(Color.DarkGray)
                    .padding(6.dp)
                    .clickable {
                        coroutineScope.launch {
                            listState.animateScrollToItem(0)
                        }
                    },
                fontSize = 28.sp,
                color = Color.White
            )
        }
    }
}

data class PersonModel(val name: String, val secondName: String, val role: String)