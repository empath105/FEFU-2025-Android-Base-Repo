package co.feip.fefu2025.data

import co.feip.fefu2025.R
import co.feip.fefu2025.domain.models.Anime

object AnimeData {
    fun getAnimeList(): List<Anime> {
        return listOf(
            Anime(
                id = 1,
                title = "BOCCHI THE ROCK!",
                rating = "9.2",
                genres = listOf("COMEDY", "MUSIC", "SLICE OF LIFE"),
                imageResId = R.drawable.bocchitherock,
                year = "2022",
                season = "1",
                episodes = "12",
                description = "Хитори Гото с детства мечтает играть в рок-группе и ради этого чуть ли не в совершенстве овладела игрой на электрогитаре. К несчастью, исключительные навыки так и не принесли ей ни единого друга. Однако, возможно, её мечта осуществится благодаря встрече с Нидзикой Идзити — девушкой, которая играет на ударных и ищет гитариста для своей группы…",
                ratings = listOf(100, 80, 180, 220, 260, 270, 300, 340, 450, 500),
                recommendationIds = listOf(2, 3, 4)
            ),
            Anime(
                id = 2,
                title = "ELFEN LIED",
                rating = "7.8",
                genres = listOf("ROMANCE", "DRAMA", "HORROR"),
                imageResId = R.drawable.elfenlied,
                year = "2004",
                description = "Темная история о девушке с псионическими способностями, сбежавшей из секретной лаборатории. Её путь пересекается с двумя людьми, которые не подозревают, насколько опасной она может быть. Кровавые сцены, трагическое прошлое и вопросы человечности делают этот сериал запоминающимся.",
                season = "1",
                episodes = "12",
                ratings = listOf(100, 80, 180, 220, 260, 270, 300, 340, 450, 500),
                recommendationIds = listOf(1, 3, 4)
            ),
            Anime(
                id = 3,
                title = "GLEIPNIR",
                rating = "6.9",
                genres = listOf("DETECTIVE", "DARK FANTASY"),
                imageResId = R.drawable.gleipnir,
                year = "2020",
                description = "Сюжет рассказывает о парне, который может превращаться в странное пушистое существо, напоминающее костюм. Однажды он встречает девушку, одержимую местью, и они начинают работать вместе, раскрывая мрачные тайны своего города.",
                season = "1",
                episodes = "13",
                ratings = listOf(80, 70, 100, 120, 150, 180, 200, 220, 250, 300),
                recommendationIds = listOf(2, 4, 1)
            ),
            Anime(
                id = 4,
                title = "BUNGOU STRAY DOGS",
                rating = "7.8",
                genres = listOf("DETECTIVE", "SUPERNATURAL"),
                imageResId = R.drawable.bungostraydogs,
                year = "2016",
                description = "Действие разворачивается вокруг Ацуши Накаджимы, изгнанного из приюта парня со сверхъестественной силой. Он присоединяется к детективному агентству, состоящему из людей с необычными способностями, и вместе они расследуют загадочные преступления.",
                season = "1",
                episodes = "12",
                ratings = listOf(120, 150, 180, 200, 220, 250, 280, 300, 350, 400),
                recommendationIds = listOf(1, 3, 2)
            ),
            Anime(
                id = 5,
                title = "CHAINSAW MAN",
                rating = "8.5",
                genres = listOf("FANTASY", "ACTION", "HORROR"),
                imageResId = R.drawable.chainsawman,
                year = "2022",
                description = "Денджи — бедный парень, который вынужден охотиться на демонов, чтобы расплатиться с долгами. После предательства он сливается с демоном-пилой и становится Челноко-Человеком, после чего его забирают в спецотряд охотников на демонов.",
                season = "1",
                episodes = "12",
                ratings = listOf(200, 250, 300, 350, 400, 450, 500, 550, 600, 650),
                recommendationIds = listOf(1, 4, 6)
            ),
            Anime(
                id = 6,
                title = "HAIKYUU!!",
                rating = "8.4",
                genres = listOf("SPORT", "COMEDY"),
                imageResId = R.drawable.huikui,
                year = "2014",
                description = "Шоу Хината, несмотря на небольшой рост, мечтает стать великим волейболистом, как его кумир «Маленький гигант». Он поступает в школу, где когда-то играл его заклятый соперник Кагеяма, и вместе они стремятся привести команду к победе.",
                season = "1",
                episodes = "25",
                ratings = listOf(150, 180, 200, 220, 250, 280, 300, 330, 360, 400),
                recommendationIds = listOf(5, 7, 8)
            ),
            Anime(
                id = 7,
                title = "JUJUTSU KAISEN",
                rating = "8.6",
                genres = listOf("ACTION", "SUPERNATURAL"),
                imageResId = R.drawable.jujutsukaisen,
                year = "2020",
                description = "Итадори Юдзи — обычный школьник, который после смерти деда попадает в мир проклятий и экзорцистов. Проглотив палец могучего проклятия, он становится его сосудом и присоединяется к школе дзюдзюцу, чтобы уничтожать зло.",
                season = "1",
                episodes = "24",
                ratings = listOf(180, 200, 220, 250, 280, 300, 330, 360, 400, 450),
                recommendationIds = listOf(1, 5, 6)
            ),
            Anime(
                id = 8,
                title = "TENGOKU DAIMAKYOU",
                rating = "8.2",
                genres = listOf("FANTASY", "ADVENTURES"),
                imageResId = R.drawable.nebesnayastena,
                year = "2023",
                description = "Постапокалиптический мир, где люди живут среди руин цивилизации, а за стенами городов скрываются чудовища. Главные герои отправляются в путешествие, чтобы найти «рай», но сталкиваются с жестокими тайнами этого мира.",
                season = "1",
                episodes = "13",
                ratings = listOf(100, 120, 150, 180, 200, 220, 250, 280, 300, 320),
                recommendationIds = listOf(3, 5, 7)
            ),
            Anime(
                id = 9,
                title = "NORAGAMI",
                rating = "7.9",
                genres = listOf("ACTION", "SUPERNATURAL"),
                imageResId = R.drawable.noragami,
                year = "2014",
                description = "Ято — бог без храма, который мечтает о славе и поклонниках. Однажды он знакомится с девушкой Хиёри, которая после несчастного случая начинает видеть потусторонний мир. Вместе с оружием-регалией Юкиной они выполняют заказы людей.",
                season = "1",
                episodes = "12",
                ratings = listOf(90, 100, 120, 150, 180, 200, 220, 250, 280, 300),
                recommendationIds = listOf(4, 6, 8)
            ),
            Anime(
                id = 10,
                title = "MAHOU SHOUJO SITE",
                rating = "6.5",
                genres = listOf("ACTION", "DRAMA", "HORROR"),
                imageResId = R.drawable.saitvolshebnic,
                year = "2018",
                description = "Ая Асагири — школьница, над которой издеваются одноклассники. Однажды она получает доступ к «Сайту магических девочек», где таинственный персонаж даёт ей магическое оружие. Но за силу приходится платить страшную цену.",
                season = "1",
                episodes = "12",
                ratings = listOf(70, 80, 90, 100, 120, 150, 180, 200, 220, 250),
                recommendationIds = listOf(2, 3, 9)
            ),
            Anime(
                id = 11,
                title = "KAKEGURUI",
                rating = "7.2",
                genres = listOf("DRAMA", "THRILLER", "DETECTIVE"),
                imageResId = R.drawable.kakegurui,
                year = "2017",
                description = "Академия Хяккао — школа для элиты, где статус ученика определяется его успехами в азартных играх. Новенькая Дзюбари Ямоме, одержимая азартом, встряхивает иерархию, играя по своим безумным правилам.",
                season = "1",
                episodes = "12",
                ratings = listOf(80, 100, 120, 150, 180, 200, 220, 250, 280, 300),
                recommendationIds = listOf(4, 7, 10)
            )

        )
    }
}