package com.pogreb.pivmetr.repository

import com.pogreb.pivmetr.model.PivModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class InMemoryPivRepository : PivRepository {
    private val state = MutableStateFlow(
        List(10) {
            PivModel(
                id = 1L,
                name = "Немецкое",
                characteristic = "Россия, 0.48 л., 4.6%",
                rating = 2.1F,
                description = "Пиво «Немецкое» сварено по рецепту известного немецкого пивовара Маркуса Бикеля. Пиво «Немецкое» светлое сварено для особых ценителей пенного напитка по традиционной немецкой технологии из светлого ячменного пивоваренного солода и отборного хмеля, выращенного в Германии. Пиво обладает светло-золотистым цветом, приятным ароматом и легкой, быстропроходящей горчинкой. Оцените в полной мере вкус сорта, приготовленного в лучших традициях европейского пивоварения.\n" +
                        "\n" +
                        "Данная информация носит исключительно ознакомительный характер. Характеристика и внешний вид товара в магазинах могут отличаться от указанных на сайте. Актуальные цены и количество товаров уточняйте в магазинах. Не является публичной офертой."
            )
        }
            .reversed()
    )


    override fun getPiv(): Flow<List<PivModel>> = state.asStateFlow()
    override fun favoriteById(id: Long) {
        state.update {
            it.map { piv ->
                if (piv.id == id) {
                    piv.copy(favorite = !piv.favorite)
                } else {
                    piv
                }
            }
        }
    }

}