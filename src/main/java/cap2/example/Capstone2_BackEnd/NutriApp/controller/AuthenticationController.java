//package cap2.example.Capstone2_BackEnd.NutriApp.controller;
//
//
//import cap2.example.Capstone2_BackEnd.NutriApp.service.AuthenticationService;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import cap2.example.Capstone2_BackEnd.NutriApp.request.LoginRequest;
//
//
//@RestController
//@RequestMapping("/auth")
//public class AuthenticationController {
//    private final AuthenticationService authenticationService;
//
//    public AuthenticationController(AuthenticationService authenticationService) {
//        this.authenticationService = authenticationService;
//    }
//
//    @PostMapping("/login")
//    public ResponseEntity<String> login(@RequestBody LoginRequest request) {
//        boolean result = authenticationService.authenticate(request);
//        return result ? ResponseEntity.ok("Login successfully") : ResponseEntity.badRequest().body("Login failed");
//    }
//}
