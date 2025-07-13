package it.epicode.gestioneViaggi.cloudinary;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import it.epicode.gestioneViaggi.dipendente.Dipendente;
import it.epicode.gestioneViaggi.dipendente.DipendenteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/api/images")
@RequiredArgsConstructor
public class CloudinaryController {
    private final Cloudinary cloudinary;
    
    @Autowired
    private DipendenteRepository dipendenteRepository;

    @PutMapping(path="/autori/{id}/avatar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Dipendente aggiornaAvatar(@PathVariable Long id, @RequestParam MultipartFile avatarFile) throws IOException {
        Dipendente dipendente = dipendenteRepository.findById(id).orElseThrow();

        Map result = cloudinary.uploader().upload(
                avatarFile.getBytes(),
                ObjectUtils.asMap("folder", "dipendenti", "public_id", "avatar_" + id)
        );

        dipendente.setAvatarUrl(result.get("secure_url").toString());

        return dipendenteRepository.save(dipendente);
    }
}