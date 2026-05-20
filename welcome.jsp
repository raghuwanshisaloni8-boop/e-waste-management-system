<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>E-Waste Dashboard</title>

<style>

*{
    margin:0;
    padding:0;
    box-sizing:border-box;
    font-family:Arial;
}

body{
    background:#ecfdf5;
}

.navbar{
    width:100%;
    background:#16a085;
    padding:18px 40px;
    display:flex;
    justify-content:space-between;
    align-items:center;
    color:white;
}

.logo{
    font-size:28px;
    font-weight:bold;
}

.logout-btn{
    text-decoration:none;
    background:white;
    color:#16a085;
    padding:10px 18px;
    border-radius:8px;
    font-weight:bold;
}

.hero{
    text-align:center;
    padding:40px 20px;
}

.hero h1{
    color:#16a085;
    font-size:42px;
    margin-bottom:10px;
}

.hero p{
    color:#555;
    font-size:18px;
}

.search-box{
    text-align:center;
    margin-bottom:30px;
}

.search-box input{
    width:350px;
    padding:12px;
    border-radius:8px;
    border:1px solid #ccc;
    font-size:16px;
}

.container{
    width:90%;
    margin:auto;
    display:grid;
    grid-template-columns:repeat(auto-fit,minmax(250px,1fr));
    gap:25px;
    padding-bottom:50px;
}

.card{
    background:white;
    border-radius:15px;
    overflow:hidden;
    box-shadow:0 0 15px rgba(0,0,0,0.2);
    transition:0.3s;
}

.card:hover{
    transform:translateY(-5px);
}

.card img{
    width:100%;
    height:220px;
    object-fit:cover;
}

.card-body{
    padding:20px;
    text-align:center;
}

.card-body h2{
    color:#16a085;
    margin-bottom:10px;
}

.card-body p{
    color:#555;
    margin-bottom:15px;
}

.pickup-btn{
    background:#16a085;
    color:white;
    border:none;
    padding:12px 18px;
    border-radius:8px;
    cursor:pointer;
    font-size:15px;
}

.pickup-btn:hover{
    background:#138d75;
}

</style>
</head>

<body>

<div class="navbar">

<div class="logo">
♻️ E-Waste Management
</div>

<a href="LogoutServlet" class="logout-btn">
Logout
</a>

</div>

<div class="hero">

<h1>E-Waste Items</h1>

<p>
Manage and recycle old electronic products easily
</p>

</div>

<div class="search-box">

<input type="text"
placeholder="Search E-Waste Products">

</div>

<div class="container">

<div class="card">

<img src="images/phone.jpg">

<div class="card-body">

<h2>Old Mobile</h2>

<p>Condition : Used</p>

<button class="pickup-btn">
Request Pickup
</button>

</div>
</div>

<div class="card">

<img src="images/laptop.jpg">

<div class="card-body">

<h2>Broken Laptop</h2>

<p>Condition : Damaged</p>

<button class="pickup-btn">
Request Pickup
</button>

</div>
</div>

<div class="card">

<img src="images/keyboard.jpeg">

<div class="card-body">

<h2>Used Keyboard</h2>

<p>Condition : Working</p>

<button class="pickup-btn">
Request Pickup
</button>

</div>
</div>

<div class="card">

<img src="images/charger.jpg">

<div class="card-body">

<h2>Old Charger</h2>

<p>Condition : Non Working</p>

<button class="pickup-btn">
Request Pickup
</button>

</div>
</div>

</div>

</body>
</html>