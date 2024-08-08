package com.example.fai_edunext.command;

import com.example.fai_edunext.entity.Account;
import com.example.fai_edunext.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
public class UpdateProfileCommand {
    private String oldPassword;
    private String newPassword;
    private String firstName;

    private String lastName;

    private String phone;

    private String address;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;

    public UpdateProfileCommand(Account account, User user){
        this.oldPassword = account.getPassword();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.phone = user.getPhone();
        this.address = user.getAddress();
        this.birthday = user.getBirthday();
    }
}
