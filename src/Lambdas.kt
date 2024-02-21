typealias DoubleConversion = (Double) -> Double

fun main(args: Array<String>){
    val addFive = {x: Int -> x + 5}
    println("Pass 6 to addFive: ${addFive(6)}")

    val addInts = { x: Int, y: Int -> x + y }
    val result = addInts.invoke(6, 7)
    println("Pass 6, 7 to addInts: $result")

    val intLambda: (Int, Int) -> Int = { x, y -> x * y }
    println("Pass 10, 11 to intLambda: ${intLambda(10, 11)}")

    val addSeven: (Int) -> Int = { it + 7 }
    println("Pass 12 to addSeven: ${addSeven(12)}")

    val myLambda: () -> Unit = { println("Hi") }
    myLambda()

    println(convert(20.0){it * 1.8 + 32})
    convertFive { it * 1.8 + 32 }

    println("Convert 2.5kg to Pounds: ${getConversionLambda("KgsToPounds")(2.5)}")

    val kgsToPoundsLambda = getConversionLambda("KgsToPounds")
    val poundsToUSTonsLambda = getConversionLambda("PoundsToUSTons")

    val kgsToUSTonsLambda = combine(kgsToPoundsLambda, poundsToUSTonsLambda)

    val value = 17.4
    println("$value kgs is ${convert(value, kgsToUSTonsLambda)} US tons")
    cookies.forEach{println(it.name)}

    val fullMenu = cookies.map {
        "${it.name} - $${it.price}"
    }
    fullMenu.forEach{println(it)}

    val softBakedMenu = cookies.filter {
        it.softBaked
    }
    softBakedMenu.forEach{ println(it.name) }

    /*val groupedMenu = cookies.groupBy { it.softBaked }
    val softBakedMenu = groupedMenu[true] ?: listOf()
    val crunchyMenu = groupedMenu[false] ?: listOf()

    println("Soft cookies:")
    softBakedMenu.forEach { println("${it.name} $${it.price}") }
    println("Crunchy cookies:")
    crunchyMenu.forEach { println("${it.name} - $${it.price}") }*/

    val totalPrice = cookies.fold(0.0){
        acc, cookie -> acc + cookie.price
    }
    println(totalPrice)

    val alphabeticalMenu = cookies.sortedBy { it.name }
    println("Alphabetical menu: ")
    alphabeticalMenu.forEach { println(it.name) }
}

fun convert(x: Double, converter: DoubleConversion): Double{
    return converter(x)
}

fun convertFive(converter: (Int) -> Double) : Double{
    val result = converter(5)
    println("5 is converted to $result")
    return result
}

fun getConversionLambda(str: String): DoubleConversion {
    if (str == "CentigradeToFahrenheit"){
        return { it * 1.8 + 32 }
    } else if (str == "KgsToPounds"){
        return { it * 2.204623 }
    } else if(str == "PoundsToUSTons"){
        return { it / 2000.0 }
    } else {
        return { it }
    }
}

fun combine(lambda1: DoubleConversion,
            lambda2: DoubleConversion) : DoubleConversion {
    return { x: Double -> lambda2(lambda1(x))}
}

class Cookie(
    val name: String,
    val softBaked: Boolean,
    val hasFilling: Boolean,
    val price: Double
)

val cookies = listOf(
    Cookie(
        name = "Chocolate Chip",
        softBaked = false,
        hasFilling = false,
        price = 1.69
    ),
    Cookie(
        name = "Banana Walnut",
        softBaked = true,
        hasFilling = false,
        price = 1.49
    ),
    Cookie(
        name = "Vanilla Creme",
        softBaked = false,
        hasFilling = true,
        price = 1.59
    ),
    Cookie(
        name = "Chocolate Peanut Butter",
        softBaked = false,
        hasFilling = true,
        price = 1.69
    ),
    Cookie(
        name = "Snickerdooble",
        softBaked = true,
        hasFilling = true,
        price = 1.79
    ),
    Cookie(
        name = "Blueberry Tart",
        softBaked = true,
        hasFilling = true,
        price = 1.79
    ),
    Cookie(
        name = "Sugar and Sprinkles",
        softBaked = false,
        hasFilling = false,
        price = 1.39
    )
)

