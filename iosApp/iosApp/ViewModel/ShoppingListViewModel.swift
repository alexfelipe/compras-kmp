//
//  ShoppingListViewModel.swift
//  iosApp
//
//  Created by Alex Felipe on 10/10/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation

class ShoppingListViewModel: ObservableObject {
    
    @Published private var products: [Product] = []
    @Published var productText = ""
    
    var productsToBuy: [Product] {
        return products.filter { !$0.wasBought }
    }
    
    var boughtProducts: [Product] {
        return products.filter { $0.wasBought }
    }
    
    func toggleProduct(product: Product) {
        let productIndex = products.firstIndex { p in
                    p.id == product.id
                }
                guard let productIndex else { return }
                products[productIndex].wasBought.toggle()
        objectWillChange.send()
    }
    
    func save() {
        if products.first(where: { $0.name.uppercased() == productText.uppercased() }) == nil {
            products.insert(Product(name: productText, wasBought: false), at: 0)
        }
        productText = ""
    }
}
