// let : 데이터 형식을 따지지 않는 변수
// let으로 선언한 변수 재할당이 가능
// 다른 타입으로도 재할당이 되지만, 권장하지 않는다.
let foo = 'a let variable'
console.log(foo)
foo = 'let variable can be reallocated'
console.log(foo)
foo = 10
console.log(foo)

// const : 불변하는 변수
// const 변수는 재할당이 불가능하다.
const bar = 'a const variable'
console.log(bar)

// 호이스팅 (hoisting)
console.log(testvar)