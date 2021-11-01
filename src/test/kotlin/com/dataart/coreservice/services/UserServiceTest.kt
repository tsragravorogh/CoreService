package com.dataart.coreservice.services


class UserServiceTest {
    // 1) успешная регистрация
    // 2) неуспешная регистрация по разным причинам (много кейсов)
    // 3) успешный логин
    // 4) неуспешный логин по разным причинам (много кейсов)

    // 1) 200 OK, json
    // 2) 409, wrongEmail, check exceptions
    // 3) 200 OK, json
    // 4) 403 wrongEmail or password, check exceptions





//    @Autowired val testRest = TestRestTemplate()
//
//    @MockBean
//    private lateinit var userRepository: UserRepository
//
//    @Autowired
//    private lateinit var userService: UserService
//
//    private val textValueLength = 6;
//
//    @Test
//    fun `Verify that User is registered`() {
//        val email = RandomStringUtils.randomAlphabetic(textValueLength)
//        val password = RandomStringUtils.randomAlphabetic(textValueLength)
//        val name = RandomStringUtils.randomAlphabetic(textValueLength)
//        val surname = RandomStringUtils.randomAlphabetic(textValueLength)
//
//        runCatching {
//            userService.registerUser(RegisterDto(name, surname, email, password))
//        }.isSuccess
//    }
//
//    @Test
//    fun `Verify that UserAlreadyExist is thrown during registration` () {
//        val email = RandomStringUtils.randomAlphabetic(textValueLength)
//
//        val user = User(RandomStringUtils.randomAlphabetic(textValueLength),
//            RandomStringUtils.randomAlphabetic(textValueLength),
//            email,
//            RandomStringUtils.randomAlphabetic(textValueLength), "")
//
//        val newUserRegisterDto = RegisterDto(
//            RandomStringUtils.randomAlphabetic(textValueLength),
//            RandomStringUtils.randomAlphabetic(textValueLength),
//            email,
//            RandomStringUtils.randomAlphabetic(textValueLength))
//
//
//
//        runCatching {
//            every { userRepository.findByEmail(email) } returns Optional.of(user)
//            userService.registerUser(newUserRegisterDto)
//        }.onFailure {
//            (it is UserAlreadyExistException).shouldBe(true)
//        }
//    }
}
