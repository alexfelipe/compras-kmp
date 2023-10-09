import SwiftUI
import shared

struct ContentView: View {
	let greet = Greeting().greet()

	var body: some View {
        ShoppingList(products: Array(sampleProducts[1...3]))
	}
}
