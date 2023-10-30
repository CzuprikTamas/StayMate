package com.example.stay_mate.controller;

import com.example.stay_mate.model.PartnerAdmin;
import com.example.stay_mate.service.PartnerAdminService;
import com.example.stay_mate.service.PartnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/partner-admin")
public class PartnerAdminController {
    private final PartnerAdminService partnerAdminService;
    private final PartnerService partnerService;

    public PartnerAdminController(PartnerAdminService partnerAdminService, PartnerService partnerService) {
        this.partnerAdminService = partnerAdminService;
        this.partnerService = partnerService;
    }

    @GetMapping("/new/{partner-id}")
    public String createPartnerAdmin(Model model, @PathVariable("partner-id") Integer partnerId) {
        model.addAttribute("partnerId", partnerId);
        model.addAttribute("new_partner_admin", new PartnerAdmin());
        return "new-partner-admin-form";
    }

    @PostMapping("/add/{partner-id}")
    public String createPartnerAdmin(@ModelAttribute("new_partner_admin") PartnerAdmin partnerAdmin, @PathVariable("partner-id") Integer partnerId) {
        partnerAdmin.setPartner(partnerService.getPartnerById(partnerId));
        partnerAdminService.savePartnerAdmin(partnerAdmin);
        return "redirect:/partner";
        // LIA: itt nem tudom mi lenne a megfelelő. Menjen a HOME oldalra, vagy a Partner oldalára?
    }

    @GetMapping("/update/{id}")
    public String updatePartnerAdmin(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("partner_admin", partnerAdminService.getPartnerAdminById(id));
        return "partner-admin-update";
    }

    @PostMapping("/update/{id}")
    public String updatePartnerAdmin(@ModelAttribute("partner_admin") PartnerAdmin updated, @PathVariable Integer id) {
        partnerAdminService.savePartnerAdmin(updated);
        return "redirect:/partner/partner/" + id;
    }
}
