//
//  ShoppingList.swift
//  iosApp
//
//  Created by Alex Felipe on 09/10/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct ShoppingList: View {
    
    @ObservedObject private var viewModel = ShoppingListViewModel()
    
    var body: some View {
        VStack {
            ZStack {
                Spacer()
                TextField(
                    "Digite o item que deseja adicionar",
                    text: $viewModel.productText
                ).padding(16)
                Spacer()
            }
            Rectangle()
                .fill(Color(hex: 0xf3AE0AE))
                .frame(width: 120, height: 48)
                .cornerRadius(8.0)
                .overlay(
                    Text("Salvar item")
                        .foregroundColor(.white)
                ).onTapGesture(perform: {
                    viewModel.save()
                })
            ScrollView {
                if(!viewModel.productsToBuy.isEmpty) {
                    LazyVStack {
                        
                        VStack {
                            HStack{
                                Text("Lista de compras")
                                    .foregroundColor(Color(hex: 0xf4574E3))
                                    .font(.system(size: 32))
                                Spacer()
                            }
                            DashedLineView()
                        }.padding(16)
                        ForEach(viewModel.productsToBuy, id: \.name){ product in
                            ProductItem(product: product, onTap: {
                                viewModel.toggleProduct(product: product)
                            })
                        }
                    }
                }
                if (!viewModel.boughtProducts.isEmpty) {
                LazyVStack {
                    
                        VStack {
                            HStack{
                                Text("Comprados")
                                    .foregroundColor(Color(hex: 0xf4574E3))
                                    .font(.system(size: 32))
                                Spacer()
                            }
                            DashedLineView()
                        }.padding(16)
                        ForEach(Array(viewModel.boughtProducts), id: \.name){ product in
                            ProductItem(product: product, onTap: {
                                viewModel.toggleProduct(product: product)
                            })
                        }

                }
                }
            }
        }
    }
}

#Preview {
    ShoppingList()
}

struct DashedLineView: View {
    var body: some View {
        GeometryReader { geometry in
            Path { path in
                path.move(to: CGPoint(x: 0, y: 0))
                path.addLine(to: CGPoint(x: geometry.size.width, y: 0))
            }
            .stroke(Color.blue, style: StrokeStyle(lineWidth: 5, dash: [5, 10]))
        }
        .frame(height: 1)
        .padding(.vertical, 10)
    }
}
