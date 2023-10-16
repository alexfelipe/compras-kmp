//
//  ShoppingListViewModel.swift
//  iosApp
//
//  Created by Alex Felipe on 10/10/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared
import KMPNativeCoroutinesAsync
import KMPNativeCoroutinesCore

@MainActor
class ShoppingListViewModel: ObservableObject {
    
    private let repository = ProductsRepository()
    @Published private var _productsToBuy: [Product] = []
    @Published private var _boughtProducts: [Product] = []
    @Published var productText = ""
    
    init() {
        Task {
            do {
                let sequence = asyncSequence(for: repository.boughtProducts)
                    .map { entities in
                        entities.map { entity in
                            Product(
                                id: entity.id,
                                name: entity.name,
                                wasBought: entity.wasBought
                            )
                        }
                    }
                for try await newProducts in sequence {
                    print("bought: \(newProducts)")
                    _boughtProducts = newProducts
                }
            } catch {
                print("Error: \(error)")
            }
        }

        Task {
            do {
                let sequence = asyncSequence(for: repository.productsToBuy)
                    .map { entities in
                        entities.map { entity in
                            Product(
                                id: entity.id,
                                name: entity.name,
                                wasBought: entity.wasBought
                            )
                        }
                    }
                for try await newProducts in sequence {
                    print("toBuy: \(newProducts)")
                    _productsToBuy = newProducts
                }
            } catch {
                print("Error: \(error)")
            }
        }
    }
    
    var productsToBuy: [Product] {
        _productsToBuy
    }
    
    var boughtProducts: [Product] {
        _boughtProducts
    }
    
    func toggleProduct(product: Product) {
        repository.toggleWasBought(id: product.id.uuidString)
    }
    
    func save() {
        repository.save(name: productText)
        productText = ""
    }
    
    func delete(product: Product) {
        repository.delete(id: product.id.uuidString)
    }
}
