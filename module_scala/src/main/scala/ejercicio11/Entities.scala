package ejercicio11

import java.time.LocalDate

case class Product(
                    id: Int,
                    name: String,
                    category: String,
                    var price: Double
                  )

case class Customer(
                     id: Int,
                     var name: String,
                     nivel: String
                   )

case class Order(
                  id: Int,
                  status: String,
                  orderDate: LocalDate,
                  deliveryDate: LocalDate,
                  products: Array[Product],
                  customer: Customer
                )