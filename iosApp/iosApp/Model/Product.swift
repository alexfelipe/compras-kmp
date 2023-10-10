//
//  Product.swift
//  iosApp
//
//  Created by Alex Felipe on 09/10/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation

class Product: Identifiable {
    
    let id = UUID()
    let name: String
    @Published var wasBought: Bool
    let dateTime: Date
    
    init(name: String) {
        self.name = name
        self.wasBought = false
        self.dateTime = Date()
    }
    
    init(name: String, wasBought: Bool) {
        self.name = name
        self.wasBought = wasBought
        self.dateTime = Date()
    }

    func format() -> String {
        let formatter = DateFormatter()
        formatter.dateFormat = "MMM dd, yyyy"
        return formatter.string(from: dateTime)
    }
}

let sampleProducts: [Product] = [
    Product(name: "Arroz", wasBought: Bool.random()),
    Product(name: "Feijão", wasBought: Bool.random()),
    Product(name: "Açúcar", wasBought: Bool.random()),
    Product(name: "Café", wasBought: Bool.random()),
    Product(name: "Farinha de Trigo", wasBought: Bool.random()),
    Product(name: "Leite", wasBought: Bool.random()),
    Product(name: "Óleo de Soja", wasBought: Bool.random()),
    Product(name: "Carne", wasBought: Bool.random()),
    Product(name: "Peixe", wasBought: Bool.random()),
    Product(name: "Frutas", wasBought: Bool.random()),
    Product(name: "Legumes", wasBought: Bool.random()),
    Product(name: "Pão", wasBought: Bool.random()),
    Product(name: "Queijo", wasBought: Bool.random()),
    Product(name: "Macarrão", wasBought: Bool.random()),
    Product(name: "Sabão em Pó", wasBought: Bool.random()),
    Product(name: "Sabonete", wasBought: Bool.random()),
    Product(name: "Shampoo", wasBought: Bool.random()),
    Product(name: "Condicionador", wasBought: Bool.random()),
    Product(name: "Escova de Dentes", wasBought: Bool.random()),
    Product(name: "Papel Higiênico", wasBought: Bool.random())
]

