//
//  ShoppingList.swift
//  iosApp
//
//  Created by Alex Felipe on 09/10/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct ShoppingList: View {
    
    @State private var productName: String = ""
    let products: [Product]
    
    var body: some View {
        VStack {
            ZStack {
                Spacer()
                TextField(
                    "Digite o item que deseja adicionar",
                    text: $productName
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
                    
                })
            ScrollView {
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
                    ForEach(products, id: \.name){ product in
                        ProductItem(product: product)
                    }
                    VStack {
                        HStack{
                            Text("Comprados")
                                .foregroundColor(Color(hex: 0xf4574E3))
                                .font(.system(size: 32))
                            Spacer()
                        }
                        DashedLineView()
                    }.padding(16)
                    ForEach(Array(sampleProducts[3...6]), id: \.name){ product in
                        ProductItem(product: product)
                    }
                }
            }
        }
    }
}

#Preview {
    ShoppingList(products: Array(sampleProducts[1...3]))
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
