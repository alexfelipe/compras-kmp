//
//  Product.swift
//  iosApp
//
//  Created by Alex Felipe on 09/10/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation

struct Product {
    let name: String
    let wasBought: Bool = false
    let dateTime: Date = Date()

    func format() -> String {
        let formatter = DateFormatter()
        formatter.dateFormat = "MMM dd, yyyy"
        return formatter.string(from: dateTime)
    }
}

let sampleProducts: [Product] = [
    Product(name: "Arroz"),
    Product(name: "Feijão"),
    Product(name: "Açúcar"),
    Product(name: "Café"),
    Product(name: "Farinha de Trigo"),
    Product(name: "Leite"),
    Product(name: "Óleo de Soja"),
    Product(name: "Carne"),
    Product(name: "Peixe"),
    Product(name: "Frutas"),
    Product(name: "Legumes"),
    Product(name: "Pão"),
    Product(name: "Queijo"),
    Product(name: "Macarrão"),
    Product(name: "Sabão em Pó"),
    Product(name: "Sabonete"),
    Product(name: "Shampoo"),
    Product(name: "Condicionador"),
    Product(name: "Escova de Dentes"),
    Product(name: "Papel Higiênico")
]
