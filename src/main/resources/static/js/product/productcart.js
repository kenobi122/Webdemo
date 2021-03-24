    
	var a = document.getElementsByClassName("addcart");
		for( let i = 0 ; i < a.length ; i++){
				
				a[i].addEventListener('click',function(){
					let cartname = list[i].name;
					let cartprice = list[i].price;
					let cartid = list[i].id;
					let products = [];
					if(localStorage.getItem('product')){
						products.JSON.parse(localStorage.getItem('product'));	
					}
					for(let u = 0 ; u < products.length; u ++){
						if(products[u].Productid == cartid){
							products[u].Number = products[u].Number + 1;
							break;
						}else{
							addproduct();
						}
						
					}
					
				
										
				});		
		}
		function addproduct(){
			
			products.push({Productid : cartid , Name: cartname , Price: cartprice , Number: 1});
			localStorage.setItem('product',JSON.stringly(products));
			
		} 
		b = document.getElementsByClassName("x");
		for(let i = , i < b.length; i++){
			b[i].addEventListener('click',function(){
				let product[];
				let a = 2;
				let b = a+2;
				product.push({name:a , king:b});
				localStorage.setItem('products',JSON.stringly(product));
				
			});
			
		}