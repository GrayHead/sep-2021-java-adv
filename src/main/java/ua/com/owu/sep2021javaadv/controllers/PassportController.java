package ua.com.owu.sep2021javaadv.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.owu.sep2021javaadv.dao.PassportDAO;
import ua.com.owu.sep2021javaadv.models.dto.PassportDTO;
import ua.com.owu.sep2021javaadv.models.entity.Passport;
import ua.com.owu.sep2021javaadv.models.entity.User;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/passports")
@AllArgsConstructor
public class PassportController {
    private PassportDAO passportDAO;

    @PostMapping
    public ResponseEntity<PassportDTO> savePassportOrWithUser(@RequestBody Passport passport) {

        Passport updatedPassport = passportDAO.save(passport);
        User passportUser = passport.getUser();
        if (passportUser != null) {
            updatedPassport.getUser().setPassport(updatedPassport);
            passportDAO.save(updatedPassport);
        }
        PassportDTO passportDTO = new PassportDTO(updatedPassport);
        return new ResponseEntity<>(passportDTO, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<PassportDTO>> getPassportsOnly() {
        List<PassportDTO> passportDTOList = passportDAO.findAll().stream().map(PassportDTO::new).collect(Collectors.toList());
        return new ResponseEntity<>(passportDTOList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PassportDTO> getPassportOnly(@PathVariable int id) {
        Passport passport = passportDAO.findById(id).orElseThrow(() -> new RuntimeException("no data"));
        return new ResponseEntity<PassportDTO>(new PassportDTO(passport), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<PassportDTO> updatePassportOnly(@PathVariable int id, @RequestBody Passport passport) {
        return new ResponseEntity<>(new PassportDTO(passportDAO.save(passport)), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<List<PassportDTO>> deletePassport(@PathVariable int id) {
        passportDAO.deleteById(id);
        List<PassportDTO> collect = passportDAO.findAll().stream().map(PassportDTO::new).collect(Collectors.toList());
        return new ResponseEntity<>(collect, HttpStatus.OK);
    }


}
