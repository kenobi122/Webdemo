/**
 * 
 */
 let product= [];
 if(localStorage.getItem('cart')){
 	product = JSON.parse(localStorage.getItem('cart'));
 }
 
 //show cart 
 window.onload = function(){
 for(let i=0; i < product.length; i++){
 	let id = product[i].cartid;
 	let name = product[i].cartname;
 	let price = product[i].cartprice;
 	let quantity = product[i].quantity;
 	
 	addRow(id, name, price, quantity);
 	updatecart();	
 	changecart();
 }
 }
 
 function addRow(id, name, price, quantity) {
 	var cartrow = document.createElement('div');
 	cartrow.classList.add('cart-row');
 	var cartitems = document.getElementById("cartrent");
 	 	
 	 	var cartRowcontent = 
            	 `<div class="cart-item cart-column">
            		<img class="cart-item-image" src=${name} >
            		<span class="cart-item-title">  ${name} </span>
              </div>
              <span class="cart-price cart-column">${price}</span>
               <div class="cart-quantity cart-column">
                    <input class="cart-quantity-input" type="number" min="1" max="10" value="${quantity}">
                    <button class="btn btn-danger" type="button">Xóa</button>
                </div> `;
			
	cartrow.innerHTML = cartRowcontent;
	cartitems.append(cartrow);
 	 	}

 	 	function updatecart(){
 	 		var total = 0;
 	 		var tong = 0;
 	 		for(let i=0; i<product.length; i++){
 	 			total = total + product[i].cartprice*product[i].quantity;
 	 		}	
 	 		 document.getElementById("my-total-cart").innerHTML = total;
 	 		
 	 	}
 	 	function changecart(){
 	 		var x = document.getElementsByClassName("cart-quantity-input");
 	 		for(let i =0; i < x.length; i++){
 	 			x[i].addEventListener('change', function(){
 	 				y = x[i].value;
 	 				product[i].quantity = y ;
 	 				localStorage.setItem('cart',JSON.stringify(product));
 	 				updatecart();
 	 			});
 	 		}
 	 		
 	 	}
 	 	
 	 	
 	 