@PostMapping("/register")
public ResponseEntity<User> register(@RequestBody RegisterRequest request) {
    // Convert RegisterRequest -> UserDTO
    UserDTO userDTO = new UserDTO();
    userDTO.setUsername(request.getUsername());
    userDTO.setEmail(request.getEmail());
    userDTO.setPassword(request.getPassword());
    userDTO.setRole(request.getRole());

    User registeredUser = userService.register(userDTO);
    return ResponseEntity.ok(registeredUser);
}
