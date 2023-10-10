//
//  ShoppingListViewModel.swift
//  iosApp
//
//  Created by Alex Felipe on 10/10/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation

class ShoppingListViewModel: ObservableObject {
    
    @Published private var products: [Product] = Array(sampleProducts.shuffled()[0...10])
    @Published var productText = ""
    
    var productsToBuy: [Product] {
        return products.filter { !$0.wasBought }
    }
    
    var boughtProducts: [Product] {
        return products.filter { $0.wasBought }
    }
    
    func toggleProduct(product: Product) {
        products = products.map({ p in
            if(p.id == product.id){
                p.wasBought.toggle()
            }
            return p
        })
    }
    
    func save() {
        if products.first(where: { $0.name.uppercased() == productText.uppercased() }) == nil {
            products.insert(Product(name: productText, wasBought: false), at: 0)
        }
        productText = ""
    }
}
