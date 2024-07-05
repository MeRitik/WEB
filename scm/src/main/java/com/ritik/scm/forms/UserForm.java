package com.ritik.scm.forms;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class UserForm {
    @NotBlank(message = "User name cannot be empty")
    private String name;

    @Email(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "Invalid Email Address")
    @Size(min = 6)
    private String email;

    @NotBlank(message = "Password cannot be empty")
    @Size(min = 6, message = "Min 6 Characters are required")
    private String password;

    @NotBlank(message = "About cannot be empty")
    private String about;

    @Size(min = 8, max = 12, message = "Invalid Phone Number")
    @Pattern(regexp = "[0-9]+")
    private String phoneNumber;
}
