//
//  ProductItem.swift
//  iosApp
//
//  Created by Alex Felipe on 09/10/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct ProductItem: View {
    
    let product: Product
    @State private var check: Bool = true

    var body: some View {
        HStack() {
            VStack(alignment: .leading) {
                HStack(spacing: 8) {
                    Button(action: {
                        check = !check
                    }) {
                        ZStack {
                            RoundedRectangle(cornerRadius: 2)
                                .stroke(Color.black, lineWidth: 2)
                                .background(Color.clear)
                                .frame(width: 20, height: 20)
                            if check {
                                Image(systemName: "checkmark")
                                    .foregroundColor(Color.green)
                            }
                        }
                    }
                    Text(product.name)
                        .font(.system(size: 18, weight: .semibold))
                }
                Text(product.format())
                    .foregroundColor(product.wasBought ? .gray : .black)
                    .strikethrough(product.wasBought, color: .gray)
            
            }
            Spacer()
            HStack {
                Image(systemName: "trash")
                    .foregroundColor(.red)
                Image(systemName: "pencil")
                    .foregroundColor(.blue)
            }
        }
        .padding()
    }
}

#Preview {
    ProductItem(product: Product(name: "teste"))
}
