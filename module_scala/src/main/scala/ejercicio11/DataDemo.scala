package ejercicio11


import java.time.LocalDate

object DataDemo {

  def mockOrders(): Array[Order] = {
    Array(
      Order(100001,
        "Pendiente",
        LocalDate.of(2021, 12, 30),
        LocalDate.of(2022, 1, 2),
        Array(
          Product(1, "Pelota Nro 5", "Pelotas", 120),
          Product(5, "Pelota Volley", "Pelotas", 93),
          Product(13, "Tobilleras", "Accesorios", 19)
        ),
        Customer(1002, "Michael Bacilio", "Plata")
      ),
      Order(100002,
        "Pendiente",
        LocalDate.of(2022, 3, 11),
        LocalDate.of(2022, 3, 11),
        Array(
          Product(8, "Ropa de Basquet", "Ropa", 193),
          Product(4, "Pelota Basquet", "Pelotas", 230),
          Product(11, "Gorra", "Accesorios", 44)
        ),
        Customer(1000, "Ernesto Salazar", "Bronce"),
      ),
      Order(100010,
        "Pendiente",
        LocalDate.of(2022, 3, 19),
        LocalDate.of(2022, 3, 20),
        Array(
          Product(8, "Ropa de Basquet", "Ropa", 193)
        ),
        Customer(1002, "Michael Bacilio", "Plata")
      ),
      Order(100019,
        "Pendiente",
        LocalDate.of(2022, 3, 11),
        LocalDate.of(2022, 3, 11),
        Array(
          Product(8, "Ropa de Basquet", "Ropa", 193),
          Product(4, "Pelota Basquet", "Pelotas", 230),
          Product(11, "Gorra", "Accesorios", 44)
        ),
        Customer(1002, "Michael Bacilio", "Plata")
      ),
      Order(100003,
        "Pendiente",
        LocalDate.of(2022, 5, 10),
        LocalDate.of(2022, 5, 16),
        Array(
          Product(10, "Guantes", "Accesorios", 320)
        ),
        Customer(1002, "Michael Bacilio", "Plata")
      ),
      Order(100004,
        "Pendiente",
        LocalDate.of(2022, 5, 18),
        LocalDate.of(2022, 5, 19),
        Array(
          Product(9, "Musleras", "Accesorios", 32),
          Product(11, "Gorra", "Accesorios", 44),
          Product(12, "Candilleras", "Accesorios", 34),
          Product(13, "Tobilleras", "Accesorios", 19),
          Product(14, "Lentes de sol", "Accesorios", 18)
        ),
        Customer(1003, "Abigail Solano", "Oro")
      ),
      Order(100005,
        "Pendiente",
        LocalDate.of(2022, 5, 18),
        LocalDate.of(2022, 5, 19),
        Array(
          Product(1, "Pelota Nro 5", "Pelotas", 120),
          Product(2, "Pelota Nro 4", "Pelotas", 110),
          Product(3, "Pelota Nro 3", "Pelotas", 93),
          Product(4, "Pelota Basquet", "Pelotas", 230),
          Product(5, "Pelota Volley", "Pelotas", 93),
        ),
        Customer(1001, "Kelly Aguirre", "Plata"),
      ),
      Order(100006,
        "Pendiente",
        LocalDate.of(2022, 5, 22),
        LocalDate.of(2022, 5, 23),
        Array(
          Product(1, "Pelota Nro 5", "Pelotas", 120),
          Product(2, "Pelota Nro 4", "Pelotas", 110),
          Product(3, "Pelota Nro 3", "Pelotas", 93),
          Product(4, "Pelota Basquet", "Pelotas", 230),
          Product(5, "Pelota Volley", "Pelotas", 93),
        ),
        Customer(1001, "Kelly Aguirre", "Plata"),
      ),
      Order(100097,
        "Pendiente",
        LocalDate.of(2022, 5, 20),
        LocalDate.of(2022, 5, 23),
        Array(
          Product(6, "Ropa de futbol", "Ropa", 130),
          Product(7, "Ropa de volley", "Ropa", 110),
          Product(8, "Ropa de Basquet", "Ropa", 193),
          Product(9, "Musleras", "Accesorios", 32),
          Product(10, "Guantes", "Accesorios", 320)
        ),
        Customer(1003, "Abigail Solano", "Oro")
      ),
    )
  }

  def mockProducts(): Array[Product] = {

    Array(
      Product(1, "Pelota Nro 5", "Pelotas", 120),
      Product(2, "Pelota Nro 4", "Pelotas", 110),
      Product(3, "Pelota Nro 3", "Pelotas", 93),
      Product(4, "Pelota Basquet", "Pelotas", 230),/// ESTE
      Product(5, "Pelota Volley", "Pelotas", 93),

      Product(6, "Ropa de futbol", "Ropa", 130),
      Product(7, "Ropa de volley", "Ropa", 110),
      Product(8, "Ropa de Basquet", "Ropa", 193), /// ESTE

      Product(9, "Musleras", "Accesorios", 32),
      Product(10, "Guantes", "Accesorios", 320), /// ESTE
      Product(11, "Gorra", "Accesorios", 44),
      Product(12, "Candilleras", "Accesorios", 34),
      Product(13, "Tobilleras", "Accesorios", 19),
      Product(14, "Lentes de sol", "Accesorios", 18)
    )

  }

  def mockCustomers(): Array[Customer] = {
    Array(
      Customer(1000, "Ernesto Salazar", "Bronce"),
      Customer(1001, "Kelly Aguirre", "Plata"),
      Customer(1002, "Michael Bacilio", "Plata"),
      Customer(1003, "Abigail Solano", "Oro")
    )
  }
}
