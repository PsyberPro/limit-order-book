package za.co.rmb.repository.cntrlr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import za.co.rmb.repository.domain.Side;
import za.co.rmb.repository.repo.SideRepository;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/side")
public class SideController {

    private SideRepository sideRepository;

    @Autowired
    public SideController(final SideRepository sideRepository) {
        this.sideRepository = sideRepository;
    }

    @GetMapping("/all")
    public List<Side> findAll() {
        List<Side> result = new ArrayList<>();
        sideRepository.findAll().forEach(result::add);
        return result;
    }
}
