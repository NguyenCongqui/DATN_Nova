package com.example.duantotnghiepgiaythethaonova.controller.admin.SanPham;
import com.example.duantotnghiepgiaythethaonova.constant.pageContants;
import com.example.duantotnghiepgiaythethaonova.constant.OptionContants;
import com.example.duantotnghiepgiaythethaonova.dto.*;
import com.example.duantotnghiepgiaythethaonova.dto.composite.HinhAnhMauSacDTO;
import com.example.duantotnghiepgiaythethaonova.dto.composite.SanPhamManageDTO;
import com.example.duantotnghiepgiaythethaonova.dto.composite.SanPhamProductManageDTO;
import com.example.duantotnghiepgiaythethaonova.dto.search.SPAndSPCTSearchDto;
import com.example.duantotnghiepgiaythethaonova.entity.*;
import com.example.duantotnghiepgiaythethaonova.service.*;
import org.apache.poi.hpsf.Decimal;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.Format;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("admin/product")
public class SanPhamChiTietController {
	@Autowired
	private SanPhamChiTietService sanPhamChiTietService;

	@Autowired
	private SanPhamService sanPhamService;

	@Autowired
	private MauSacService mauSacService;

	@Autowired
	private ChatLieuService chatLieuService;

	@Autowired
	private KichCoService kichCoService;

	@Autowired
	private DayGiayService dayGiayService;

	@Autowired
	private DeGiayService deGiayService;

	@Autowired
	private LotGiayService lotGiayService;

	@Autowired
	private ThuongHieuService thuongHieuService;

	@Autowired
	private KieuDangService kieuDangService;

	@Autowired
	private StorageService storageService;

	@Autowired
	private HinhAnhService hinhAnhService;

//	private ProductDetailsWithColorSizeRepository productDetailsWithColorSizeRepository;

	@GetMapping("/images/{filename:.+}")
	@ResponseBody
	public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
		Resource file = storageService.loadAsResource(filename);

		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
				.body(file);
	}

	@ModelAttribute("lstMauSac")
	public List<MauSacDTO> getLstMauSac() {
		return mauSacService.selectAllMauSacExist().stream().map(item -> {
			MauSacDTO dto = new MauSacDTO();
			BeanUtils.copyProperties(item, dto);
			return dto;
		}).collect(Collectors.toList());
	}

	@ModelAttribute("lstKieuDang")
	public List<KieuDangDTO> getLstKieuDang() {
		return kieuDangService.selectAllKieuDangExist().stream().map(item -> {
			KieuDangDTO dto = new KieuDangDTO();
			BeanUtils.copyProperties(item, dto);
			return dto;
		}).collect(Collectors.toList());
	}

	@ModelAttribute("lstChatLieu")
	public List<ChatLieuDTO> getLstChatLieu() {
		return chatLieuService.selectAllChatLieuExist().stream().map(item -> {
			ChatLieuDTO dto = new ChatLieuDTO();
			BeanUtils.copyProperties(item, dto);
			return dto;
		}).collect(Collectors.toList());
	}

	@ModelAttribute("lstKichCo")
	public List<KichCoDTO> getLstKichCo() {
		return kichCoService.selectAllKichCoExist().stream().map(item -> {
			KichCoDTO dto = new KichCoDTO();
			BeanUtils.copyProperties(item, dto);
			return dto;
		}).collect(Collectors.toList());
	}

	@ModelAttribute("lstDayGiay")
	public List<DayGiayDTO> getLstDayGiay() {
		return dayGiayService.selectAllKichCoExist().stream().map(item -> {
			DayGiayDTO dto = new DayGiayDTO();
			BeanUtils.copyProperties(item, dto);
			return dto;
		}).collect(Collectors.toList());
	}

	@ModelAttribute("lstDeGiay")
	public List<DeGiayDTO> getLstDeGiay() {
		return deGiayService.selectAllKichCoExist().stream().map(item -> {
			DeGiayDTO dto = new DeGiayDTO();
			BeanUtils.copyProperties(item, dto);
			return dto;
		}).collect(Collectors.toList());
	}

	@ModelAttribute("lstLotGiay")
	public List<LotGiayDTO> getLstLotGiay() {
		return lotGiayService.selectAllKichCoExist().stream().map(item -> {
			LotGiayDTO dto = new LotGiayDTO();
			BeanUtils.copyProperties(item, dto);
			return dto;
		}).collect(Collectors.toList());
	}

	@ModelAttribute("lstThuongHieu")
	public List<ThuongHieuDTO> getLstLoaiHang() {
		return thuongHieuService.selectAllLoaiHangExist().stream().map(item -> {
			ThuongHieuDTO dto = new ThuongHieuDTO();
			BeanUtils.copyProperties(item, dto);
			return dto;
		}).collect(Collectors.toList());
	}

	@GetMapping("")
	public String search(ModelMap model, @ModelAttribute(name = "dataSearch") SPAndSPCTSearchDto dataSearch,
						 @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size,
						 @RequestParam("messageSuccess") Optional<String> messageSuccess,
						 @RequestParam("messageDanger") Optional<String> messageDanger) {

		int currentPage = page.orElse(1);
		int pageSize = size.orElse(10);

		Pageable pageable = PageRequest.of(currentPage - 1, pageSize);
		Page<SanPham> resultPage = null;
		Optional<SPAndSPCTSearchDto> optDataSearch = Optional.of(dataSearch);
		if (optDataSearch.isPresent()) {
			resultPage = sanPhamService.searchProductExist(dataSearch, pageable);
			model.addAttribute("dataSearch", dataSearch);
			List<SanPhamProductManageDTO> lstDto = new ArrayList<>();
			for (SanPham sp : resultPage.getContent()) {
				SanPhamProductManageDTO dto = new SanPhamProductManageDTO();
				int tongSoLuong = sanPhamChiTietService.getSumSoLuongBySanPhamId(sp.getIdSanPham());
				System.out.println(sanPhamChiTietService.getSumSoLuongBySanPhamId(sp.getIdSanPham()));
				dto.setTongSoLuong(tongSoLuong);
				dto.setSanPham(sp);
				List<HinhAnh> lstAnhChinh = hinhAnhService.getHinhAnhChinhBySanPhamId(sp.getIdSanPham());
				List<String> anhChinhs = lstAnhChinh.stream().map(HinhAnh::getTenAnh).collect(Collectors.toList());
				dto.setAnhChinhs(anhChinhs);
				BigDecimal giaBan = sanPhamChiTietService.getTienBan(sp.getIdSanPham());
				System.out.println(sanPhamChiTietService.getTienBan(sp.getIdSanPham()));
				dto.setGiaBan(giaBan);
				lstDto.add(dto);
			}
			model.addAttribute("sanPhams", lstDto);
		}

		int totalPages = resultPage.getTotalPages();
		if (totalPages > 0) {
			int start = Math.max(1, currentPage - 2);
			int end = Math.min(currentPage + 2, totalPages);
			if (totalPages > 5) {
				if (end == totalPages) {
					start = end - 5;
				} else if (start == 1) {
					end = start + 5;
				}
			}
			List<Integer> pageNumbers = IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		}
		if (messageSuccess.isPresent()) {
//			if(!messageSuccess.isEmpty()) {
			model.addAttribute("messageSuccess", messageSuccess.get());
//			}
		}
		if (messageSuccess.isPresent()) {
//			if(!messageDanger.isEmpty()) {
			model.addAttribute("messageDanger", messageDanger.get());
//			}
		}
		model.addAttribute("sanPhamPage", resultPage);
		return "admin/product/productManage";
	}

	@GetMapping("info/{id}")
	public String infoProductDetai(ModelMap model, @PathVariable("id") Integer id, RedirectAttributes redirect) {
		SanPhamDTO dto = new SanPhamDTO();
		Optional<SanPham> opt = sanPhamService.findById(id);
		List<ChiTietSanPham> lstSp = sanPhamChiTietService.getLstChiTietSanPhamBySanPhamId(id);
		if(opt.isPresent()) {
			dto.setSanPhamChiTiets(lstSp);
//			dto.setGia(opt.get().getGia());
			dto.setMaSanPham(opt.get().getMaSanPham());
			dto.setTenChatLieu(opt.get().getChatLieu().getTenChatLieu());
			dto.setTenKieuDang(opt.get().getKieuDang().getTenKieuDang());
			dto.setTenThuongHieu(opt.get().getThuongHieu().getTenThuongHieu());
			dto.setTenSanPham(opt.get().getTenSanPham());
			dto.setMoTa(opt.get().getMoTa());
			List<HinhAnh> ha = hinhAnhService.getHinhAnhBySanPhamId(id);
			if (opt.isPresent()) {
				model.addAttribute("sanPham", dto);
				model.addAttribute("imgs", ha);
			}
			return "admin/product/infoProduct";
		}else {
			redirect.addFlashAttribute("messageDanger","Sản phẩm không tồn tại");
			return "redirect:/admin/product";
		}
	}

	@GetMapping("add")
	public String addProductDetail(ModelMap model,
								   @ModelAttribute("sanPhamManageDTO") Optional<SanPhamManageDTO> sanPhamManageDTO) {
		SanPhamManageDTO sanPhamManageDTONew = new SanPhamManageDTO();
		if (sanPhamManageDTO.isPresent()) {
			sanPhamManageDTONew.setChatLieuId(sanPhamManageDTO.get().getChatLieuId());
			sanPhamManageDTONew.setGia(sanPhamManageDTO.get().getGia());
			sanPhamManageDTONew.setKichCoIds(sanPhamManageDTO.get().getKichCoIds());
			sanPhamManageDTONew.setKieuDangId(sanPhamManageDTO.get().getKieuDangId());
			sanPhamManageDTONew.setThuongHieuId(sanPhamManageDTO.get().getThuongHieuId());
			sanPhamManageDTONew.setMauSacIds(sanPhamManageDTO.get().getMauSacIds());
			sanPhamManageDTONew.setDayGiayId(sanPhamManageDTO.get().getDayGiayId());
			sanPhamManageDTONew.setDeGiayId(sanPhamManageDTO.get().getDeGiayId());
			sanPhamManageDTONew.setLotGiayId(sanPhamManageDTO.get().getLotGiayId());
			sanPhamManageDTONew.setMoTa(sanPhamManageDTO.get().getMoTa());
			sanPhamManageDTONew.setSanPhamId(sanPhamManageDTO.get().getSanPhamId());
			sanPhamManageDTONew.setSoLuong(sanPhamManageDTO.get().getSoLuong());
			sanPhamManageDTONew.getMaSanPham();
			sanPhamManageDTONew.setTenSanPham(sanPhamManageDTO.get().getTenSanPham());
		}
		model.addAttribute("sanPhamManageDTO", sanPhamManageDTONew);
		return "admin/product/addProduct";
	}

	@PostMapping("generateProductDetails")
	public String generateProductDetails(ModelMap model,
										 @Valid @ModelAttribute("sanPhamManageDTO") SanPhamManageDTO data, BindingResult result) {
		if (result.hasErrors()) {
			model.addAttribute("sanPhamManageDTO", data);
			return "admin/product/addProduct";
		} else {
			SanPham sanPham = new SanPham();
			sanPham.setDaXoa(false);
//			sanPham.setGia(data.getGia());
			sanPham.getMaSanPham();
			sanPham.setTenSanPham(data.getTenSanPham());
			sanPham.setMoTa(data.getMoTa());

			ChatLieu chatLieu = new ChatLieu();
			chatLieu.setIdChatLieu(data.getChatLieuId());
			sanPham.setChatLieu(chatLieu);

			ThuongHieu thuongHieu = new ThuongHieu();
			thuongHieu.setIdThuongHieu(data.getThuongHieuId());
			sanPham.setThuongHieu(thuongHieu);


			KieuDang kieuDang = new KieuDang();
			kieuDang.setIdKieuDang(data.getKieuDangId());
			sanPham.setKieuDang(kieuDang);

			sanPhamService.save(sanPham);

			for (Integer kichCoId : data.getKichCoIds()) {
				for (Integer mauSacId : data.getMauSacIds()) {
					ChiTietSanPham spct = new ChiTietSanPham();
					spct.setCoHienThi(true);
					spct.setDaXoa(false);
					spct.setSanPham(sanPham);
					int soLuong = data.getSoLuong();
					BigDecimal gia = data.getGia();
					spct.setSoLuong(soLuong);
					spct.setGia(gia);

					KichCo kichCo = new KichCo();
					kichCo.setIdKichCo(kichCoId);
					spct.setKichCo(kichCo);

					MauSac mauSac = new MauSac();
					mauSac.setIdMauSac(mauSacId);
					spct.setMauSac(mauSac);

					DayGiay dayGiay = new DayGiay();
					dayGiay.setIdDayGiay(data.getDayGiayId());
					spct.setDayGiay(dayGiay);

					DeGiay deGiay = new DeGiay();
					deGiay.setIdDeGiay(data.getDeGiayId());
					spct.setDeGiay(deGiay);

					LotGiay lotGiay = new LotGiay();
					lotGiay.setIdLotGiay(data.getLotGiayId());
					spct.setLotGiay(lotGiay);


					sanPhamChiTietService.save(spct);
				}
			}
			List<ChiTietSanPham> dataGen = sanPhamChiTietService.getLstChiTietSanPhamBySanPhamId(sanPham.getIdSanPham());
			dataGen.forEach(i -> {
				Optional<MauSac> optMS = mauSacService.findById(i.getMauSac().getIdMauSac());
				Optional<KichCo> optKC = kichCoService.findById(i.getKichCo().getIdKichCo());
				Optional<DayGiay> optDG = dayGiayService.findById(i.getDayGiay().getIdDayGiay());
				Optional<DeGiay> optEG = deGiayService.findById(i.getDeGiay().getIdDeGiay());
				Optional<LotGiay> optLG = lotGiayService.findById(i.getLotGiay().getIdLotGiay());

				i.setMauSac(optMS.get());
				i.setKichCo(optKC.get());
				i.setDayGiay(optDG.get());
				i.setDeGiay(optEG.get());
				i.setLotGiay(optLG.get());
			});
			List<MauSac> lstMauSacAddImg = mauSacService.getAllMauSacExistBySPId(sanPham.getIdSanPham());
			//button - mau sac add img
			HashMap<Integer, String> lstMauSacAddImgHM = new HashMap<Integer, String>();
			for (MauSac mauSac : lstMauSacAddImg) {
				lstMauSacAddImgHM.put(mauSac.getIdMauSac(), mauSac.getTenMauSac());
			}
			data.setLstMauSacAddImg(lstMauSacAddImgHM);

			data.setSanPhamId(sanPham.getIdSanPham());
			data.setIsGenaratedData(true);
			model.addAttribute("dataGen", dataGen);
			model.addAttribute("messageSuccess", "Tạo sản phẩm và chi tiết sản phẩm thành công");
			model.addAttribute("sanPhamManageDTO", data);
			return "/admin/product/addProduct";
		}
	}

	@GetMapping("changeIsShowFormAddProduct/{id}")
	@ResponseBody
	public ResponseEntity<?> changeIsShowFormAddProduct(ModelMap model, @PathVariable("id") Integer id) {
		Optional<ChiTietSanPham> opt = sanPhamChiTietService.findById(id);
		if (opt.isPresent()) {
			opt.get().setCoHienThi(!opt.get().getCoHienThi());
			sanPhamChiTietService.save(opt.get());
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

	}

	@GetMapping("edit/{id}")
	public String edit(ModelMap model, @PathVariable("id") Integer id,
					   @RequestParam("messageDanger") Optional<String> messageDanger,
					   @RequestParam("messageSuccess") Optional<String> messageSuccess) {
		Optional<SanPham> optSP = sanPhamService.findById(id);
		if (optSP.isPresent()) {
			List<ChiTietSanPham> dataGen = sanPhamChiTietService.getLstChiTietSanPhamBySanPhamId(optSP.get().getIdSanPham());
			SanPhamManageDTO dto = new SanPhamManageDTO();
			//button - mau sac add img
			List<MauSac> lstMauSacAddImg = mauSacService.getAllMauSacExistBySPId(id);
			HashMap<Integer, String> lstMauSacAddImgHM = new HashMap<Integer, String>();
			for (MauSac mauSac : lstMauSacAddImg) {
				lstMauSacAddImgHM.put(mauSac.getIdMauSac(), mauSac.getTenMauSac());
			}
			dto.setLstMauSacAddImg(lstMauSacAddImgHM);

			BeanUtils.copyProperties(optSP.get(), dto);
			dto.setSanPhamId(optSP.get().getIdSanPham());
			dto.setIsEdit(true);
			dto.setThuongHieuId(optSP.get().getThuongHieu().getIdThuongHieu());
			dto.setKieuDangId(optSP.get().getKieuDang().getIdKieuDang());
			dto.setChatLieuId(optSP.get().getChatLieu().getIdChatLieu());
			List<Integer> lstKC = dataGen.stream().map(i -> i.getKichCo().getIdKichCo()).collect(Collectors.toList());
			List<Integer> lstMS = dataGen.stream().map(i -> i.getMauSac().getIdMauSac()).collect(Collectors.toList());
			dto.setKichCoIds(lstKC);
			dto.setMauSacIds(lstMS);

//======================================
			Integer lstEG = dataGen.stream().map(i -> i.getDeGiay().getIdDeGiay())
					.findFirst()  // Lấy giá trị đầu tiên từ danh sách
					.orElse(null);  // Hoặc giá trị mặc định nếu danh sách rỗng hoặc không có giá trị
			Integer lstLG = dataGen.stream().map(i -> i.getLotGiay().getIdLotGiay()).findFirst().orElse(null);
			Integer lstDG = dataGen.stream().map(i -> i.getDayGiay().getIdDayGiay()).findFirst().orElse(null);
			dto.setDeGiayId(lstEG);
			dto.setLotGiayId(lstLG);
			dto.setDayGiayId(lstDG);
//=======================================


			List<HinhAnh> lstHinhAnh = hinhAnhService.getLstHinhAnhMauSacBySanPhamId(id);
			List<List<HinhAnhDTO>> lstHinhAnhDTOs = new ArrayList<>();
			int i = 0;
			int j = 0;
			int countLstHinhAnh = 0;
			do {
				List<HinhAnhDTO> HinhAnhDTOs = new ArrayList<>();
				for (j = i; j < lstHinhAnh.size(); j++) {
					if (lstHinhAnh.get(j).getMauSac().getIdMauSac().equals(lstHinhAnh.get(i).getMauSac().getIdMauSac())) {
						HinhAnhDTO haDto = new HinhAnhDTO();
						BeanUtils.copyProperties(lstHinhAnh.get(j), haDto);
						haDto.setCoHienThi(lstHinhAnh.get(j).getCoHienThi());
						haDto.setLaAnhChinh(lstHinhAnh.get(j).getLaAnhChinh());
						haDto.setHinhAnhid(lstHinhAnh.get(j).getIdHinhAnh());
						HinhAnhDTOs.add(haDto);
					} else {
						i = j;
						break;
					}
				}
				countLstHinhAnh++;

				lstHinhAnhDTOs.add(HinhAnhDTOs);
				if (j == lstHinhAnh.size())
					break;
			} while (j < lstHinhAnh.size());
			int m = 0;
			List<HinhAnhMauSacDTO> lstHinhAnhMauSacDTO = new ArrayList<>();
			List<Integer> lstHinhAnhDistinct = hinhAnhService.getDistinctMauSacInHinhAnhBySanPhamId(id);
			if (lstHinhAnhDistinct.size() != 0) {
				for (Integer mauSacId : lstHinhAnhDistinct) {
					HinhAnhMauSacDTO hinhAnhMauSacDTO = new HinhAnhMauSacDTO();
					Optional<MauSac> optMS = mauSacService.findById(mauSacId);
					if (optMS.isPresent()) {
						hinhAnhMauSacDTO.setTenMauSacAddImg(optMS.get().getTenMauSac());
						hinhAnhMauSacDTO.setMauSacAddImagesId(mauSacId);
					}
					if (m < countLstHinhAnh) {
						List<HinhAnhDTO> lstHinhAnhDTO = new ArrayList<HinhAnhDTO>();
						for (HinhAnhDTO item : lstHinhAnhDTOs.get(m)) {
							lstHinhAnhDTO.add(item);
						}
						hinhAnhMauSacDTO.setHinhAnhDTOs(lstHinhAnhDTO);
						m++;
					}
					lstHinhAnhMauSacDTO.add(hinhAnhMauSacDTO);
				}
				dto.setLstHinhAnhMauSacDTO(lstHinhAnhMauSacDTO);
			}
			dto.setIsGenaratedData(true);
			dto.setIsCreatedValueImg(true);
			dto.setIsAddProductSuccess(true);
			int count = hinhAnhService.getCountHinhAnhBySanPhamId(dto.getSanPhamId());
			if(count > 0) {
				dto.setIsHaveImg(true);
			}else dto.setIsHaveImg(false);
			model.addAttribute("sanPhamManageDTO", dto);
			model.addAttribute("dataGen", dataGen);
		}
		if (messageDanger.isPresent()) {
			model.addAttribute("messageDanger", messageDanger.get());
		}

		if (messageSuccess.isPresent()) {
			model.addAttribute("messageSuccess", messageSuccess.get());
		}
		return "admin/product/addProduct";
	}

	@PostMapping("deleteAllByIdsProductManage")
	public String deleteAllByIdProductManage(ModelMap model,
											 @ModelAttribute("dataSearch") SPAndSPCTSearchDto dataSearch, HttpServletRequest request,
											 @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size,
											 final RedirectAttributes redirectAttributes) throws IOException {
		String[] ids = request.getParameterValues("productIds");
		boolean isSuccess = false;
		if (ids != null) {
			for (String item : ids) {
				Optional<SanPham> opt = sanPhamService.findById(Integer.parseInt(item));
				if (opt.isPresent()) {
					List<HinhAnh> lstHinhAnh = hinhAnhService.getHinhAnhBySanPhamId(Integer.parseInt(item));
					for (HinhAnh ha : lstHinhAnh) {
						if (!ha.getTenAnh().isEmpty()) {
							storageService.delete(ha.getTenAnh());
							hinhAnhService.delete(ha);
						} else {
							redirectAttributes.addFlashAttribute("messageDanger",
									"Tên hình ảnh: '" + ha.getTenAnh() + "' không tồn tại");
						}
					}
					sanPhamService.delete(opt.get());
					for (ChiTietSanPham spct : opt.get().getChiTietSanPhams()) {
						sanPhamChiTietService.delete(spct);
					}
					isSuccess = true;
				} else {
					redirectAttributes.addFlashAttribute("messageDanger",
							"Xóa sản phẩm có tên: " + opt.get().getTenSanPham() + " thất bại");
					isSuccess = false;
				}
			}
		} else {
			isSuccess = false;
			redirectAttributes.addFlashAttribute("messageDanger", "Sản phẩm không tồn tại");
		}
		if (isSuccess == true) {
			redirectAttributes.addFlashAttribute("messageSuccess", "Xóa sản phẩm thành công");
			redirectAttributes.addFlashAttribute("page", page);
			redirectAttributes.addFlashAttribute("size", size);
			redirectAttributes.addFlashAttribute("dataSearch", dataSearch);
			return "redirect:/admin/product";
		} else {
			redirectAttributes.addFlashAttribute("page", page);
			redirectAttributes.addFlashAttribute("size", size);
			redirectAttributes.addFlashAttribute("dataSearch", dataSearch);
			return "redirect:/admin/product";
		}
	}

	@PostMapping("updateQuantityProductDetail")
	public String updateQuantityProductDetail(ModelMap model, @ModelAttribute("sanPhamManageDTO") SanPhamManageDTO data,
											  HttpServletRequest request, HttpServletResponse response,
											  RedirectAttributes redirect) throws IOException {
//		if(data.getGia() == null) {
//			model.addAttribute("messageDanger", "bạn không được để giá sản phẩm trống");
//			List<ChiTietSanPham> dataGen = sanPhamChiTietService.getLstChiTietSanPhamBySanPhamId(data.getSanPhamId());
//			//button - mau sac add img
//			List<MauSac> lstMauSacAddImg = mauSacService.getAllMauSacExistBySPId(data.getSanPhamId());
//			HashMap<Integer, String> lstMauSacAddImgHM = new HashMap<Integer, String>();
//			for (MauSac mauSac : lstMauSacAddImg) {
//				lstMauSacAddImgHM.put(mauSac.getIdMauSac(), mauSac.getTenMauSac());
//			}
//			data.setLstMauSacAddImg(lstMauSacAddImgHM);
//			model.addAttribute("dataGen", dataGen);
//			model.addAttribute("sanPhamManageDTO", data);
//			return "admin/product/addProduct";
//		}
		if (
//				data.getGia().toString().isEmpty()||
			data.getTenSanPham().isEmpty() || data.getMoTa().isEmpty()
				|| data.getChatLieuId() == -1 || data.getThuongHieuId() == -1
				|| data.getKieuDangId() == -1) {
			model.addAttribute("messageDanger", "bạn không được để trống thông tin sản phẩm");
			List<ChiTietSanPham> dataGen = sanPhamChiTietService.getLstChiTietSanPhamBySanPhamId(data.getSanPhamId());
			//button - mau sac add img
			List<MauSac> lstMauSacAddImg = mauSacService.getAllMauSacExistBySPId(data.getSanPhamId());
			HashMap<Integer, String> lstMauSacAddImgHM = new HashMap<Integer, String>();
			for (MauSac mauSac : lstMauSacAddImg) {
				lstMauSacAddImgHM.put(mauSac.getIdMauSac(), mauSac.getTenMauSac());
			}
			data.setLstMauSacAddImg(lstMauSacAddImgHM);
			model.addAttribute("dataGen", dataGen);
			model.addAttribute("sanPhamManageDTO", data);
			return "admin/product/addProduct";
		}
//		if(data.getGia().compareTo(BigDecimal.valueOf(1000L))<0) {
//			model.addAttribute("messageDanger", "bạn không được để giá sản phẩm < 1000");
//			List<ChiTietSanPham> dataGen = sanPhamChiTietService.getLstChiTietSanPhamBySanPhamId(data.getSanPhamId());
//			//button - mau sac add img
//			List<MauSac> lstMauSacAddImg = mauSacService.getAllMauSacExistBySPId(data.getSanPhamId());
//			HashMap<Integer, String> lstMauSacAddImgHM = new HashMap<Integer, String>();
//			for (MauSac mauSac : lstMauSacAddImg) {
//				lstMauSacAddImgHM.put(mauSac.getIdMauSac(), mauSac.getTenMauSac());
//			}
//			data.setLstMauSacAddImg(lstMauSacAddImgHM);
//			model.addAttribute("dataGen", dataGen);
//			model.addAttribute("sanPhamManageDTO", data);
//			return "admin/product/addProduct";
//		}
		boolean isSuccess = false;
		Optional<SanPham> optSP = sanPhamService.findById(data.getSanPhamId());
		if (optSP.isPresent()) {
			optSP.get().setDaXoa(false);
//			optSP.get().setGia(data.getGia());
			optSP.get().getMaSanPham();
			optSP.get().setTenSanPham(data.getTenSanPham());
			optSP.get().setMoTa(data.getMoTa());

			ChatLieu chatLieu = new ChatLieu();
			chatLieu.setIdChatLieu(data.getChatLieuId());
			optSP.get().setChatLieu(chatLieu);

			ThuongHieu thuongHieu = new ThuongHieu();
			thuongHieu.setIdThuongHieu(data.getThuongHieuId());
			optSP.get().setThuongHieu(thuongHieu);

			KieuDang kieuDang = new KieuDang();
			kieuDang.setIdKieuDang(data.getKieuDangId());
			optSP.get().setKieuDang(kieuDang);

			sanPhamService.save(optSP.get());
			isSuccess = true;
		}

		String[] soLuongs = request.getParameterValues("soLuongs");
		String[] ids = request.getParameterValues("soLuongTheoIds");
		if (soLuongs != null && ids != null) {
			for (String item : soLuongs) {
				if (!isNumeric(item)) {
					redirect.addFlashAttribute("messageDanger", "Số lượng phải là số");
					return "redirect:/admin/product/edit/"+data.getSanPhamId();
				}
				if(Integer.parseInt(item) < 1) {
					redirect.addFlashAttribute("messageDanger", "Số lượng phải lớn hơn 0");
					return "redirect:/admin/product/edit/"+data.getSanPhamId();
				}
			}
			for (String item : ids) {
				if (!isNumeric(item)) {
					redirect.addFlashAttribute("messageDanger", "Id phải là số");
					return "redirect:/admin/product/edit/"+data.getSanPhamId();
				}
			}
		}

		String[] gias = request.getParameterValues("gias");
		String[] idsgia = request.getParameterValues("giaIds");
		if (gias != null && ids != null) {
			for (String item : gias) {
				if (!isNumeric(item)) {
					redirect.addFlashAttribute("messageDanger", "Giá là số");
					return "redirect:/admin/product/edit/"+data.getSanPhamId();
				}
				if(Integer.parseInt(item) < 1) {
					redirect.addFlashAttribute("messageDanger", "Giá phải lớn hơn 0");
					return "redirect:/admin/product/edit/"+data.getSanPhamId();
				}
			}
			for (String item : ids) {
				if (!isNumeric(item)) {
					redirect.addFlashAttribute("messageDanger", "Id phải là số");
					return "redirect:/admin/product/edit/"+data.getSanPhamId();
				}
			}
		}
		// add key-id, value-gia -> map
		Map<String, String> hm1 = new HashMap<>();
		for (int i = 0; i < idsgia.length; i++) {
			hm1.put(idsgia[i], gias[i]);
		}

		// add key-id, value-soLuong -> map
		Map<String, String> hm = new HashMap<>();
		for (int i = 0; i < ids.length; i++) {
			hm.put(ids[i], soLuongs[i]);
		}
		Integer sPID = null;
		for (Map.Entry<String, String> mapItem : hm.entrySet()) {
			Optional<ChiTietSanPham> opt = sanPhamChiTietService.findById(Integer.parseInt(mapItem.getKey()));
			if (opt.isPresent()) {
				opt.get().setSoLuong(Integer.parseInt(mapItem.getValue()));
				sPID = opt.get().getSanPham().getIdSanPham();
				sanPhamChiTietService.save(opt.get());
				isSuccess = true;
			} else
				isSuccess = false;
		}
		for (Map.Entry<String, String> mapItem : hm1.entrySet()) {
			Optional<ChiTietSanPham> opt = sanPhamChiTietService.findById(Integer.parseInt(mapItem.getKey()));
			if (opt.isPresent()) {
				BigDecimal giaValue = new BigDecimal(mapItem.getValue());
				opt.get().setGia(giaValue);
				sPID = opt.get().getSanPham().getIdSanPham();
				sanPhamChiTietService.save(opt.get());
				isSuccess = true;
			} else
				isSuccess = false;
		}
		List<ChiTietSanPham> dataGen = sanPhamChiTietService.getLstChiTietSanPhamBySanPhamId(sPID);
		model.addAttribute("dataGen", dataGen);
		//button - mau sac add img
		List<MauSac> lstMauSacAddImg = mauSacService.getAllMauSacExistBySPId(data.getSanPhamId());
		HashMap<Integer, String> lstMauSacAddImgHM = new HashMap<Integer, String>();
		for (MauSac mauSac : lstMauSacAddImg) {
			lstMauSacAddImgHM.put(mauSac.getIdMauSac(), mauSac.getTenMauSac());
		}
		data.setLstMauSacAddImg(lstMauSacAddImgHM);
		List<Integer> lstKC = dataGen.stream().map(i -> i.getKichCo().getIdKichCo()).collect(Collectors.toList());
		List<Integer> lstMS = dataGen.stream().map(i -> i.getMauSac().getIdMauSac()).collect(Collectors.toList());
		data.setKichCoIds(lstKC);
		data.setMauSacIds(lstMS);

//		====================================
		Integer lstDG = dataGen.stream().map(i -> i.getDayGiay().getIdDayGiay()).findFirst().orElse(null);
		Integer lstEG = dataGen.stream().map(i -> i.getDeGiay().getIdDeGiay()).findFirst().orElse(null);
		Integer lstLG = dataGen.stream().map(i -> i.getLotGiay().getIdLotGiay()).findFirst().orElse(null);
		data.setDayGiayId(lstDG);
		data.setDeGiayId(lstEG);
		data.setLotGiayId(lstLG);
//		======================================

		List<HinhAnh> lstHinhAnh = hinhAnhService.getLstHinhAnhMauSacBySanPhamId(data.getSanPhamId());
		List<List<HinhAnhDTO>> lstHinhAnhDTOs = new ArrayList<>();
		int i = 0;
		int j = 0;
		int countLstHinhAnh = 0;
		do {
			List<HinhAnhDTO> HinhAnhDTOs = new ArrayList<>();
			for (j = i; j < lstHinhAnh.size(); j++) {
				if (lstHinhAnh.get(j).getMauSac().getIdMauSac().equals(lstHinhAnh.get(i).getMauSac().getIdMauSac())) {
					HinhAnhDTO haDto = new HinhAnhDTO();
					BeanUtils.copyProperties(lstHinhAnh.get(j), haDto);
					haDto.setCoHienThi(lstHinhAnh.get(j).getCoHienThi());
					haDto.setLaAnhChinh(lstHinhAnh.get(j).getLaAnhChinh());
					haDto.setHinhAnhid(lstHinhAnh.get(j).getIdHinhAnh());
					HinhAnhDTOs.add(haDto);
				} else {
					i = j;
					break;
				}
			}
			countLstHinhAnh++;

			lstHinhAnhDTOs.add(HinhAnhDTOs);
			if (j == lstHinhAnh.size())
				break;
		} while (j < lstHinhAnh.size());
		int m = 0;
		List<HinhAnhMauSacDTO> lstHinhAnhMauSacDTO = new ArrayList<>();
		List<Integer> lstHinhAnhDistinct = hinhAnhService.getDistinctMauSacInHinhAnhBySanPhamId(data.getSanPhamId());
		if (lstHinhAnhDistinct.size() != 0) {
			for (Integer mauSacId : lstHinhAnhDistinct) {
				HinhAnhMauSacDTO hinhAnhMauSacDTO = new HinhAnhMauSacDTO();
				Optional<MauSac> optMS = mauSacService.findById(mauSacId);
				if (optMS.isPresent()) {
					hinhAnhMauSacDTO.setTenMauSacAddImg(optMS.get().getTenMauSac());
					hinhAnhMauSacDTO.setMauSacAddImagesId(mauSacId);
				}
				if (m < countLstHinhAnh) {
					List<HinhAnhDTO> lstHinhAnhDTO = new ArrayList<HinhAnhDTO>();
					for (HinhAnhDTO item : lstHinhAnhDTOs.get(m)) {
						lstHinhAnhDTO.add(item);
					}
					hinhAnhMauSacDTO.setHinhAnhDTOs(lstHinhAnhDTO);
					m++;
				}
				lstHinhAnhMauSacDTO.add(hinhAnhMauSacDTO);
			}
			data.setLstHinhAnhMauSacDTO(lstHinhAnhMauSacDTO);
		}
		if (isSuccess == true) {
			model.addAttribute("messageSuccess", "Cập nhật thông tin sản phẩm thành công");
		} else
			model.addAttribute("messageDanger", "Cập nhật sản phẩm thất bại");
		model.addAttribute("sanPhamManageDTO", data);
		model.addAttribute("dataGen", dataGen);
		return "admin/product/addProduct";
	}

	@GetMapping("/productDetail/edit/{id}/{pageName}")
	public String editProductDetail(ModelMap model, @PathVariable("id") Integer id,
									@PathVariable("pageName") String returnUrlPage) {
		Optional<ChiTietSanPham> opt = sanPhamChiTietService.findById(id);
		if (opt.isPresent()) {
			SanPhamChiTietDTO dto = new SanPhamChiTietDTO();
			BeanUtils.copyProperties(opt.get(), dto);
			dto.setKichCoId(opt.get().getKichCo().getIdKichCo());
			dto.setMauSacId(opt.get().getMauSac().getIdMauSac());
//			dto.setDayGiayId(opt.get().getDayGiay().getIdDayGiay());
//			dto.setLotGiayId(opt.get().getLotGiay().getIdLotGiay());
//			dto.setDeGiayId(opt.get().getDeGiay().getIdDeGiay());
			dto.setSanPhamId(opt.get().getSanPham().getIdSanPham());
			if (returnUrlPage.equalsIgnoreCase(pageContants.addProduct)) {
				returnUrlPage = "/admin/product/edit/" + opt.get().getSanPham().getIdSanPham();
			} else if (returnUrlPage.equalsIgnoreCase(pageContants.infoProduct)) {
				returnUrlPage = "/admin/product/info/" + opt.get().getSanPham().getIdSanPham();
			}
			model.addAttribute("sanPhamChiTietDTO", dto);
			model.addAttribute("pageName", returnUrlPage);
		}
		return "admin/product/editProductDetail";
	}

	@GetMapping("addImageProductDetail/{mauSacIdInput}")
	public String addImageProductDetail(Model model, @ModelAttribute("sanPhamManageDTO") SanPhamManageDTO data,
										HttpServletRequest request, final RedirectAttributes redirect,
										@PathVariable("mauSacIdInput") Integer mauSacIdInput) throws IOException {
		String[] spctIds = request.getParameterValues("mauSacProductIds");
		long idSanPham = 0;
		if (spctIds != null) {
			for (String id : spctIds) {
				if (isNumeric(id)) {
					idSanPham = Integer.parseInt(id);
					break;
				}
			}
		}
		int count = hinhAnhService.getCountHinhAnhBySanPhamId((int) idSanPham);
		if(count > 0) {
			data.setIsHaveImg(true);
		}else data.setIsHaveImg(false);

		int countHinhAnhToiDaDuocThem = hinhAnhService.getCountHinhAnhChoPhepThemBySanPhamId((int) idSanPham);
		int soLuongHinhAnhHienCo = hinhAnhService.getCountHinhAnhBySanPhamId((int) idSanPham);
		if(countHinhAnhToiDaDuocThem-soLuongHinhAnhHienCo>0) {
			int hinhAnhCoTheThem = countHinhAnhToiDaDuocThem-soLuongHinhAnhHienCo;
			model.addAttribute("messageSuccess", "Số lượng hình ảnh có thể thêm là: "+hinhAnhCoTheThem+" ảnh");
			if(mauSacIdInput.equals(Integer.parseInt("0"))) {
				List<MauSac> lstMauSacAddImg = mauSacService.getAllMauSacExistBySPId((int) idSanPham);
				List<HinhAnhMauSacDTO> lstHinhAnhMauSacDTO = new ArrayList<HinhAnhMauSacDTO>();
				for (MauSac mauSac : lstMauSacAddImg) {
					List<Integer> spctIdsAddImg = new ArrayList<Integer>();
					mauSac.getChiTietSanPhams().stream().forEach(i -> {
						spctIdsAddImg.add(i.getIdCTSP());
					});
					HinhAnhMauSacDTO dto = new HinhAnhMauSacDTO();
					dto.setMauSacAddImagesId(mauSac.getIdMauSac());
					dto.setTenMauSacAddImg(mauSac.getTenMauSac());
					lstHinhAnhMauSacDTO.add(dto);
				}
				data.setLstHinhAnhMauSacDTO(lstHinhAnhMauSacDTO);
				data.setIsCreatedImg(true);
				data.setIsCreatedValueImg(false);
				model.addAttribute("sanPhamManageDTO", data);
				List<ChiTietSanPham> dataGen = sanPhamChiTietService.getLstChiTietSanPhamBySanPhamId((int) idSanPham);
				model.addAttribute("dataGen", dataGen);
				//button - mau sac add img
				HashMap<Integer, String> lstMauSacAddImgHM = new HashMap<Integer, String>();
				for (MauSac mauSac : lstMauSacAddImg) {
					lstMauSacAddImgHM.put(mauSac.getIdMauSac(), mauSac.getTenMauSac());
				}
				data.setLstMauSacAddImg(lstMauSacAddImgHM);
				return "admin/product/addProduct";
			}else {
				Optional<MauSac> optMS = mauSacService.findById(mauSacIdInput);
				List<HinhAnhMauSacDTO> lstHinhAnhMauSacDTO = new ArrayList<HinhAnhMauSacDTO>();
				if(optMS.isPresent()) {
					List<Integer> spctIdsAddImg = new ArrayList<Integer>();
					optMS.get().getChiTietSanPhams().stream().forEach(i -> {
						spctIdsAddImg.add(i.getIdCTSP());
					});
					HinhAnhMauSacDTO dto = new HinhAnhMauSacDTO();
					dto.setMauSacAddImagesId(optMS.get().getIdMauSac());
					dto.setTenMauSacAddImg(optMS.get().getTenMauSac());
					lstHinhAnhMauSacDTO.add(dto);
				}
				data.setLstHinhAnhMauSacDTO(lstHinhAnhMauSacDTO);
				data.setIsCreatedImg(true);
				data.setIsCreatedValueImg(false);
				model.addAttribute("sanPhamManageDTO", data);
				List<ChiTietSanPham> dataGen = sanPhamChiTietService.getLstChiTietSanPhamBySanPhamId((int) idSanPham);
				model.addAttribute("dataGen", dataGen);
				List<MauSac> lstMauSacAddImg = mauSacService.getAllMauSacExistBySPId((int) idSanPham);
				//button - mau sac add img
				HashMap<Integer, String> lstMauSacAddImgHM = new HashMap<Integer, String>();
				for (MauSac mauSac : lstMauSacAddImg) {
					lstMauSacAddImgHM.put(mauSac.getIdMauSac(), mauSac.getTenMauSac());
				}
				data.setLstMauSacAddImg(lstMauSacAddImgHM);
				return "admin/product/addProduct";
			}
		}else {
			redirect.addFlashAttribute("messageDanger", "Số lượng hình ảnh đang đạt tối đa ("+soLuongHinhAnhHienCo+" ảnh)");
			return "redirect:/admin/product/edit/"+idSanPham;
		}
	}

	@PostMapping("saveImageProductDetail")
	public String saveImageProductDetail(ModelMap model,
										 @ModelAttribute("sanPhamManageDTO") SanPhamManageDTO sanPhamManageDTO,
										 RedirectAttributes redirect) {
		for (HinhAnhMauSacDTO item : sanPhamManageDTO.getLstHinhAnhMauSacDTO()) {
			for (MultipartFile imgFile : item.getImgFiles()) {
				if (imgFile.isEmpty()) {
					model.addAttribute("messageDanger", "Hình ảnh cho sản phẩm không được để trống");
					List<ChiTietSanPham> dataGen = sanPhamChiTietService
							.getLstChiTietSanPhamBySanPhamId(sanPhamManageDTO.getSanPhamId());
					sanPhamManageDTO.setIsAddProductImageSuccess(true);
					model.addAttribute("dataGen", dataGen);
					//button - mau sac add img
					List<MauSac> lstMauSacAddImg = mauSacService.getAllMauSacExistBySPId(sanPhamManageDTO.getSanPhamId());
					HashMap<Integer, String> lstMauSacAddImgHM = new HashMap<Integer, String>();
					for (MauSac mauSac : lstMauSacAddImg) {
						lstMauSacAddImgHM.put(mauSac.getIdMauSac(), mauSac.getTenMauSac());
					}
					model.addAttribute("sanPhamManageDTO", sanPhamManageDTO);
					return "admin/product/addProduct";
				}
			}
		}
		int countHinhAnh = hinhAnhService.getCountHinhAnhBySanPhamId(sanPhamManageDTO.getSanPhamId());
		int countHinhAnhChoPhepThem = hinhAnhService.getCountHinhAnhChoPhepThemBySanPhamId(sanPhamManageDTO.getSanPhamId());
		int soLuongHinhAnhThem = sanPhamManageDTO.getLstHinhAnhMauSacDTO().stream().map(HinhAnhMauSacDTO::getImgFiles)
				.filter(rs -> rs != null)
				.mapToInt(List::size).sum();
		if(countHinhAnhChoPhepThem - countHinhAnh - soLuongHinhAnhThem >= 0 && countHinhAnhChoPhepThem - soLuongHinhAnhThem >= 0) {
			sanPhamManageDTO.getLstHinhAnhMauSacDTO().stream().forEach(i -> {
				Optional<MauSac> optMS = mauSacService.findById(i.getMauSacAddImagesId());
				Optional<SanPham> optSP = sanPhamService.findById(sanPhamManageDTO.getSanPhamId());
				i.getImgFiles().stream().forEach(img -> {
					UUID uuid = UUID.randomUUID();
					String uuString = uuid.toString();
					HinhAnh hinhAnh = new HinhAnh();
					hinhAnh.setTenAnh(storageService.getStoredFileName(img, uuString));
					if (optSP.isPresent()) {
						hinhAnh.setSanPham(optSP.get());
					}
					storageService.store(img, hinhAnh.getTenAnh());
					if (optMS.isPresent()) {
						List<HinhAnh> lstHA = hinhAnhService
								.getHinhAnhBySanPhamIdAndMauSacId(sanPhamManageDTO.getSanPhamId(), optMS.get().getIdMauSac());
						for (HinhAnh hinhAnhOld : lstHA) {
							hinhAnhOld.setLaAnhChinh(false);
							hinhAnhService.save(hinhAnhOld);
						}
						hinhAnh.setMauSac(optMS.get());
						hinhAnh.setCoHienThi(true);
						hinhAnh.setLaAnhChinh(true);
						hinhAnhService.save(hinhAnh);
					}
				});
			});
		}else {
			model.addAttribute("messageDanger", "Số lượng hình ảnh còn lại cho phép thêm của sản phẩm là: "+ (countHinhAnhChoPhepThem - countHinhAnh));
			List<ChiTietSanPham> dataGen = sanPhamChiTietService
					.getLstChiTietSanPhamBySanPhamId(sanPhamManageDTO.getSanPhamId());
			sanPhamManageDTO.setIsAddProductImageSuccess(true);
			model.addAttribute("dataGen", dataGen);
			//button - mau sac add img
			List<MauSac> lstMauSacAddImg = mauSacService.getAllMauSacExistBySPId(sanPhamManageDTO.getSanPhamId());
			HashMap<Integer, String> lstMauSacAddImgHM = new HashMap<Integer, String>();
			for (MauSac mauSac : lstMauSacAddImg) {
				lstMauSacAddImgHM.put(mauSac.getIdMauSac(), mauSac.getTenMauSac());
			}
			model.addAttribute("sanPhamManageDTO", sanPhamManageDTO);
			return "admin/product/addProduct";
		}

		List<HinhAnh> lstHinhAnh = hinhAnhService.getLstHinhAnhMauSacBySanPhamId(sanPhamManageDTO.getSanPhamId());
		List<List<HinhAnhDTO>> lstHinhAnhDTOs = new ArrayList<>();
		int i = 0;
		int j = 0;
		int countLstHinhAnh = 0;
		do {
			List<HinhAnhDTO> HinhAnhDTOs = new ArrayList<>();
			for (j = i; j < lstHinhAnh.size(); j++) {
				if (lstHinhAnh.get(j).getMauSac().getIdMauSac().equals(lstHinhAnh.get(i).getMauSac().getIdMauSac())) {
					HinhAnhDTO haDto = new HinhAnhDTO();
					BeanUtils.copyProperties(lstHinhAnh.get(j), haDto);
					haDto.setCoHienThi(lstHinhAnh.get(j).getCoHienThi());
					haDto.setLaAnhChinh(lstHinhAnh.get(j).getLaAnhChinh());
					haDto.setHinhAnhid(lstHinhAnh.get(j).getIdHinhAnh());
					HinhAnhDTOs.add(haDto);
				} else {
					i = j;
					break;
				}
			}
			countLstHinhAnh++;

			lstHinhAnhDTOs.add(HinhAnhDTOs);
			if (j == lstHinhAnh.size())
				break;
		} while (j < lstHinhAnh.size());
		int m = 0;
		List<HinhAnhMauSacDTO> lstHinhAnhMauSacDTO = new ArrayList<>();
		List<Integer> lstHinhAnhDistinct = hinhAnhService
				.getDistinctMauSacInHinhAnhBySanPhamId(sanPhamManageDTO.getSanPhamId());
		for (Integer mauSacId : lstHinhAnhDistinct) {
			HinhAnhMauSacDTO hinhAnhMauSacDTO = new HinhAnhMauSacDTO();
			Optional<MauSac> optMS = mauSacService.findById(mauSacId);
			if (optMS.isPresent()) {
				hinhAnhMauSacDTO.setTenMauSacAddImg(optMS.get().getTenMauSac());
				hinhAnhMauSacDTO.setMauSacAddImagesId(mauSacId);
			}
			if (m < countLstHinhAnh) {
				List<HinhAnhDTO> lstHinhAnhDTO = new ArrayList<HinhAnhDTO>();
				for (HinhAnhDTO item : lstHinhAnhDTOs.get(m)) {
					lstHinhAnhDTO.add(item);
				}
				hinhAnhMauSacDTO.setHinhAnhDTOs(lstHinhAnhDTO);
				m++;
			}
			lstHinhAnhMauSacDTO.add(hinhAnhMauSacDTO);
		}
		sanPhamManageDTO.setIsCreatedImg(false);
		sanPhamManageDTO.setIsCreatedValueImg(true);
		sanPhamManageDTO.setLstHinhAnhMauSacDTO(lstHinhAnhMauSacDTO);
		List<ChiTietSanPham> dataGen = sanPhamChiTietService
				.getLstChiTietSanPhamBySanPhamId(sanPhamManageDTO.getSanPhamId());
		sanPhamManageDTO.setIsAddProductImageSuccess(true);
		model.addAttribute("dataGen", dataGen);
		//button - mau sac add img
		List<MauSac> lstMauSacAddImg = mauSacService.getAllMauSacExistBySPId(sanPhamManageDTO.getSanPhamId());
		HashMap<Integer, String> lstMauSacAddImgHM = new HashMap<Integer, String>();
		for (MauSac mauSac : lstMauSacAddImg) {
			lstMauSacAddImgHM.put(mauSac.getIdMauSac(), mauSac.getTenMauSac());
		}
		sanPhamManageDTO.setLstMauSacAddImg(lstMauSacAddImgHM);
		model.addAttribute("sanPhamManageDTO", sanPhamManageDTO);
		if(!sanPhamManageDTO.getIsEdit()) {
			redirect.addFlashAttribute("messageSuccess", "Thêm sản phẩm thành công");
			return "redirect:/admin/product";
		}else {
			model.addAttribute("messageSuccess", "Thêm hình ảnh cho sản phẩm thành công");
			return "/admin/product/addProduct";
		}
	}

	@GetMapping("changeCoHienThi/{imgName}")
	@ResponseBody
	public ResponseEntity<String> changeCoHienThi(@PathVariable("imgName") String imgName) {
		Optional<HinhAnh> opt = hinhAnhService.getHinhAnhByName(imgName);
		if (opt.isPresent()) {
			if(opt.get().getLaAnhChinh() == true) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}else {
				opt.get().setCoHienThi(!opt.get().getCoHienThi());
				hinhAnhService.save(opt.get());
				return new ResponseEntity<>(HttpStatus.OK);
			}
		} else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	@GetMapping("changeLaAnhChinh/{imgName}")
	@ResponseBody
	public ResponseEntity<String> changeLaAnhChinh(ModelMap model, @PathVariable("imgName") String imgName) {
		Optional<HinhAnh> opt = hinhAnhService.getHinhAnhByName(imgName);
		if (opt.isPresent()) {
			int countHAChinhOld = hinhAnhService.getCountHinhAnhChinhBySanPhamIdAndMauSacId(
					opt.get().getSanPham().getIdSanPham(), opt.get().getMauSac().getIdMauSac());
			if (countHAChinhOld > 0) {
				Optional<HinhAnh> optHAChinhOld = hinhAnhService.getHinhAnhChinhBySanPhamIdAndMauSacId(
						opt.get().getSanPham().getIdSanPham(), opt.get().getMauSac().getIdMauSac());
				if (optHAChinhOld.isPresent()) {
					optHAChinhOld.get().setLaAnhChinh(false);
					hinhAnhService.save(optHAChinhOld.get());
				}
			}
			opt.get().setLaAnhChinh(true);
			opt.get().setCoHienThi(true);
			hinhAnhService.save(opt.get());
			return new ResponseEntity<>(HttpStatus.OK);

		} else
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	@GetMapping("removeHinhAnh/{imgName}")
	@ResponseBody
	public ResponseEntity<String> removeHinhAnh(ModelMap model, @PathVariable("imgName") String imgName)
			throws IOException {
		Optional<HinhAnh> opt = hinhAnhService.getHinhAnhByName(imgName);
		if (opt.isPresent()) {
			if(opt.get().getLaAnhChinh() == true) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}else {
				if (!opt.get().getTenAnh().isEmpty()) {
					storageService.delete(opt.get().getTenAnh());
					hinhAnhService.delete(opt.get());
				}
				hinhAnhService.delete(opt.get());
				return new ResponseEntity<>(HttpStatus.OK);
			}
		} else
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	@PostMapping("saveValueImageProduct")
	public ModelAndView saveProduct(ModelMap model,
									@ModelAttribute("sanPhamManageDTO") SanPhamManageDTO sanPhamManageDTO, HttpServletRequest request) {
		if (!sanPhamManageDTO.getLstHinhAnhMauSacDTO().isEmpty()) {
			sanPhamManageDTO.getLstHinhAnhMauSacDTO().stream().forEach(i1 -> {
				if (!i1.getHinhAnhDTOs().isEmpty()) {
					i1.getHinhAnhDTOs().stream().forEach(i2 -> {
						Optional<HinhAnh> opt = hinhAnhService.findById(i2.getHinhAnhid());
						if (opt.isPresent()) {
//							opt.get().setCoHienThi(i2.getCoHienThi());
//							opt.get().setLaAnhChinh(i2.getLaAnhChinh());
							hinhAnhService.save(opt.get());
						}
					});
				}
			});
		}
		List<HinhAnh> lstHinhAnh = hinhAnhService.getLstHinhAnhMauSacBySanPhamId(sanPhamManageDTO.getSanPhamId());
		List<List<HinhAnhDTO>> lstHinhAnhDTOs = new ArrayList<>();
		int i = 0;
		int j = 0;
		int countLstHinhAnh = 0;
		do {
			List<HinhAnhDTO> HinhAnhDTOs = new ArrayList<>();
			for (j = i; j < lstHinhAnh.size(); j++) {
				if (lstHinhAnh.get(j).getMauSac().getIdMauSac().equals(lstHinhAnh.get(i).getMauSac().getIdMauSac())) {
					HinhAnhDTO haDto = new HinhAnhDTO();
					BeanUtils.copyProperties(lstHinhAnh.get(j), haDto);
					haDto.setHinhAnhid(lstHinhAnh.get(j).getIdHinhAnh());
					haDto.setCoHienThi(lstHinhAnh.get(j).getCoHienThi());
					haDto.setLaAnhChinh(lstHinhAnh.get(j).getLaAnhChinh());
					HinhAnhDTOs.add(haDto);
				} else {
					i = j;
					break;
				}
			}
			countLstHinhAnh++;

			lstHinhAnhDTOs.add(HinhAnhDTOs);
			if (j == lstHinhAnh.size())
				break;
		} while (j < lstHinhAnh.size());
		int m = 0;
		List<HinhAnhMauSacDTO> lstHinhAnhMauSacDTO = new ArrayList<>();
		List<Integer> lstHinhAnhDistinct = hinhAnhService
				.getDistinctMauSacInHinhAnhBySanPhamId(sanPhamManageDTO.getSanPhamId());
		for (Integer mauSacId : lstHinhAnhDistinct) {
			HinhAnhMauSacDTO hinhAnhMauSacDTO = new HinhAnhMauSacDTO();
			Optional<MauSac> optMS = mauSacService.findById(mauSacId);
			if (optMS.isPresent()) {
				hinhAnhMauSacDTO.setTenMauSacAddImg(optMS.get().getTenMauSac());
				hinhAnhMauSacDTO.setMauSacAddImagesId(mauSacId);
			}
			if (m < countLstHinhAnh) {
//				Resource[] imgfiles = applicationContext.getre;
				List<HinhAnhDTO> lstHinhAnhDTO = new ArrayList<HinhAnhDTO>();
				for (HinhAnhDTO item : lstHinhAnhDTOs.get(m)) {
					lstHinhAnhDTO.add(item);
				}
				hinhAnhMauSacDTO.setHinhAnhDTOs(lstHinhAnhDTO);
				m++;
			}
			lstHinhAnhMauSacDTO.add(hinhAnhMauSacDTO);
		}
		sanPhamManageDTO.setIsCreatedImg(false);
		sanPhamManageDTO.setIsCreatedValueImg(true);
		sanPhamManageDTO.setLstHinhAnhMauSacDTO(lstHinhAnhMauSacDTO);
		List<ChiTietSanPham> dataGen = sanPhamChiTietService
				.getLstChiTietSanPhamBySanPhamId(sanPhamManageDTO.getSanPhamId());
		model.addAttribute("sanPhamManageDTO", sanPhamManageDTO);
		model.addAttribute("dataGen", dataGen);
		//button - mau sac add img
		List<MauSac> lstMauSacAddImg = mauSacService.getAllMauSacExistBySPId(sanPhamManageDTO.getSanPhamId());
		HashMap<Integer, String> lstMauSacAddImgHM = new HashMap<Integer, String>();
		for (MauSac mauSac : lstMauSacAddImg) {
			lstMauSacAddImgHM.put(mauSac.getIdMauSac(), mauSac.getTenMauSac());
		}
		sanPhamManageDTO.setLstMauSacAddImg(lstMauSacAddImgHM);
		return new ModelAndView("admin/product/addProduct");
	}

	@PostMapping("saveOptionValue")
	public String saveOptionValue(ModelMap model, RedirectAttributes redirect, @ModelAttribute("sanPhamManageDTO") SanPhamManageDTO sanPhamManageDTO,
								  HttpServletRequest request, HttpServletResponse response) throws IOException {
		String[] value = request.getParameterValues("thuocTinhInput");
		String[] option = request.getParameterValues("fieldthuocTinhInput");
		List<ChiTietSanPham> dataGen = sanPhamChiTietService
				.getLstChiTietSanPhamBySanPhamId(sanPhamManageDTO.getSanPhamId());
		model.addAttribute("dataGen", dataGen);
		//button - mau sac add img
		List<MauSac> lstMauSacAddImg = mauSacService.getAllMauSacExistBySPId(sanPhamManageDTO.getSanPhamId());
		HashMap<Integer, String> lstMauSacAddImgHM = new HashMap<Integer, String>();
		for (MauSac mauSac : lstMauSacAddImg) {
			lstMauSacAddImgHM.put(mauSac.getIdMauSac(), mauSac.getTenMauSac());
		}
		sanPhamManageDTO.setLstMauSacAddImg(lstMauSacAddImgHM);
		model.addAttribute("sanPhamManageDTO", sanPhamManageDTO);
		if (value != null && option != null) {
			if (!option[0].isEmpty() && !value[0].isEmpty()) {
				if (option[0].equalsIgnoreCase(OptionContants.chatLieu)) {
					ChatLieu entity = new ChatLieu();
					entity.setTenChatLieu(value[0].toString());
					chatLieuService.save(entity);
					List<ChatLieu> loadData = chatLieuService.selectAllChatLieuExist();
					model.addAttribute("lstChatLieu", loadData);
					model.addAttribute("messageSuccess", "Thêm mới chất liệu thành công");
					redirect.addFlashAttribute("messageSuccess", "Thêm mới chất liệu thành công");
				} else if (option[0].equalsIgnoreCase(OptionContants.thuongHieu)) {
					ThuongHieu entity = new ThuongHieu();
					entity.setTenThuongHieu(value[0].toString());
					thuongHieuService.save(entity);
					List<ThuongHieu> loadData = thuongHieuService.selectAllLoaiHangExist();
					model.addAttribute("lstThuongHieu", loadData);
					model.addAttribute("messageSuccess", "Thêm mới thương hiệu thành công");
					redirect.addFlashAttribute("messageSuccess", "Thêm mới loại sản phẩm thành công");
				} else if (option[0].equalsIgnoreCase(OptionContants.kichCo)) {
					KichCo entity = new KichCo();
					entity.setTenKichCo(value[0].toString());
					kichCoService.save(entity);
					List<KichCo> loadData = kichCoService.selectAllKichCoExist();
					model.addAttribute("lstKichCo", loadData);
					model.addAttribute("messageSuccess", "Thêm mới kích cỡ thành công");
					redirect.addFlashAttribute("messageSuccess", "Thêm mới kích cỡ thành công");
				} else if (option[0].equalsIgnoreCase(OptionContants.kieuDang)) {
					KieuDang entity = new KieuDang();
					entity.setTenKieuDang(value[0].toString());
					kieuDangService.save(entity);
					List<KieuDang> loadData = kieuDangService.selectAllKieuDangExist();
					model.addAttribute("lstKieuDang", loadData);
					model.addAttribute("messageSuccess", "Thêm mới kiểu dáng thành công");
					redirect.addFlashAttribute("messageSuccess", "Thêm mới kiểu dáng thành công");
				} else if (option[0].equalsIgnoreCase(OptionContants.mauSac)) {
					String[] maMauSac = request.getParameterValues("maMauSac");
					if (!maMauSac[0].isEmpty()) {
						MauSac entity = new MauSac();
						entity.setTenMauSac(value[0].toString());
						entity.setMaMauSac(maMauSac[0].toString());
						mauSacService.save(entity);
						List<MauSac> loadData = mauSacService.selectAllMauSacExist();
						model.addAttribute("lstMauSac", loadData);
						model.addAttribute("messageSuccess", "Thêm mới màu sắc thành công");
						redirect.addFlashAttribute("messageSuccess", "Thêm mới màu sắc thành công");
					} else {
						model.addAttribute("messageDanger", "Mã màu sắc không được để trống");
						return "admin/product/addProduct";
					}
				} else if (option[0].equalsIgnoreCase(OptionContants.dayGiay)) {
					DayGiay entity = new DayGiay();
					entity.setTenDayGiay(value[0].toString());
					dayGiayService.save(entity);
					List<DayGiay> loadData = dayGiayService.selectAllKichCoExist();
					model.addAttribute("lstDayGiay", loadData);
					model.addAttribute("messageSuccess", "Thêm mới dây giày thành công");
					redirect.addFlashAttribute("messageSuccess", "Thêm mới dây giày thành công");
				} else if (option[0].equalsIgnoreCase(OptionContants.deGiay)) {
					DeGiay entity = new DeGiay();
					entity.setTenDeGiay(value[0].toString());
					deGiayService.save(entity);
					List<DeGiay> loadData = deGiayService.selectAllKichCoExist();
					model.addAttribute("lstDeGiay", loadData);
					model.addAttribute("messageSuccess", "Thêm mới đế giày thành công");
					redirect.addFlashAttribute("messageSuccess", "Thêm mới đế giày thành công");
				} else if (option[0].equalsIgnoreCase(OptionContants.lotGiay)) {
					LotGiay entity = new LotGiay();
					entity.setTenLotGiay(value[0].toString());
					lotGiayService.save(entity);
					List<LotGiay> loadData = lotGiayService.selectAllKichCoExist();
					model.addAttribute("lstLotGiay", loadData);
					model.addAttribute("messageSuccess", "Thêm mới lót giày thành công");
					redirect.addFlashAttribute("messageSuccess", "Thêm mới lót giày thành công");
				}
			} else {
				model.addAttribute("messageDanger", "Tên giá trị thuộc tính không được để trống");
			}
		} else {
			model.addAttribute("messageDanger", "Lưu giá trị thuộc tính sản phẩm thất bại");
		}
		if(sanPhamManageDTO.getSanPhamId() == null) {
			return "admin/product/addProduct";
		}else return "redirect:/admin/product/edit/"+sanPhamManageDTO.getSanPhamId();
	}

	@GetMapping("delete/{id}")
	public String deleteProduct(@PathVariable("id") Integer sanPhamId, ModelMap model,
								@ModelAttribute("sanPhamManageDTO") Optional<SanPhamManageDTO> sanPhamManageDTO,
								@ModelAttribute("dataSearch") SPAndSPCTSearchDto dataSearch, @RequestParam("page") Optional<Integer> page,
								@RequestParam("size") Optional<Integer> size, final RedirectAttributes redirectAttributes)
			throws IOException {
		Optional<SanPham> opt = sanPhamService.findById(sanPhamId);
		if (opt.isPresent()) {
			List<HinhAnh> lstHinhAnh = hinhAnhService.getHinhAnhBySanPhamId(sanPhamId);
			for (HinhAnh ha : lstHinhAnh) {
				if (!ha.getTenAnh().isEmpty()) {
					storageService.delete(ha.getTenAnh());
					hinhAnhService.delete(ha);
				} else {
					redirectAttributes.addFlashAttribute("messageDanger",
							"Tên hình ảnh: '" + ha.getTenAnh() + "' không tồn tại");
				}
			}
			sanPhamService.delete(opt.get());
			for (ChiTietSanPham spct : opt.get().getChiTietSanPhams()) {
				sanPhamChiTietService.delete(spct);
			}
			redirectAttributes.addFlashAttribute("messageSuccess", "Xóa sản phẩm thành công");
			redirectAttributes.addFlashAttribute("page", page);
			redirectAttributes.addFlashAttribute("size", size);
			redirectAttributes.addFlashAttribute("dataSearch", dataSearch);
			return "redirect:/admin/product";
		} else {
			redirectAttributes.addFlashAttribute("dataSearch", dataSearch);
			redirectAttributes.addFlashAttribute("messageDanger", "Xóa sản phẩm thất bại");
			return "redirect:/admin/product";
		}
	}

	@PostMapping("deleteAllByIdsAddProduct")
	public String deleteAllByIdsAddProduct(final RedirectAttributes redirect,
										   @ModelAttribute("sanPhamManageDTO") SanPhamManageDTO sanPhamManageDTO, HttpServletRequest request)
			throws IOException {
		String[] ids = request.getParameterValues("productDetailIds");
		if (ids != null) {
			for (String item : ids) {
				Optional<ChiTietSanPham> opt = sanPhamChiTietService.findById(Integer.parseInt(item));
				if (opt.isPresent()) {
					sanPhamChiTietService.delete(opt.get());
					if(sanPhamManageDTO.getLstHinhAnhMauSacDTO() != null) {
						for (HinhAnhMauSacDTO hinhAnhMauSacDTO : sanPhamManageDTO.getLstHinhAnhMauSacDTO()) {
							int countHinhAnh = sanPhamChiTietService.getCountChiTietSanPhamExistBySanPhamIdAndMauSacId(
									sanPhamManageDTO.getSanPhamId(), hinhAnhMauSacDTO.getMauSacAddImagesId());
							if (countHinhAnh == 0) {
								List<HinhAnh> lstHA = hinhAnhService.getHinhAnhBySanPhamIdAndMauSacId(
										sanPhamManageDTO.getSanPhamId(), hinhAnhMauSacDTO.getMauSacAddImagesId());
								for (HinhAnh hinhAnh : lstHA) {
									if (!hinhAnh.getTenAnh().isEmpty()) {
										storageService.delete(hinhAnh.getTenAnh());
										hinhAnhService.delete(hinhAnh);
									} else {
										redirect.addFlashAttribute("messageDanger",
												"Tên hình ảnh: '" + hinhAnh.getTenAnh() + "' không tồn tại");
									}
								}
							}
						}
					}
					int countSPCTExist = sanPhamChiTietService
							.getCountChiTietSanPhamExistBySanPhamId(sanPhamManageDTO.getSanPhamId());
					if (countSPCTExist == 0) {
						Optional<SanPham> optSP = sanPhamService.findById(sanPhamManageDTO.getSanPhamId());
						sanPhamService.delete(optSP.get());
						redirect.addFlashAttribute("sanPhamManageDTO", sanPhamManageDTO);
					}
					redirect.addFlashAttribute("messageSuccess", "Xóa sản phẩm chi tiết thành công");
				} else {
					redirect.addFlashAttribute("messageDanger", "Xóa sản phẩm chi tiết thất bại");
				}
			}
		} else {
			redirect.addFlashAttribute("messageDanger", "Xóa sản phẩm chi tiết thất bại");
		}
		return "redirect:/admin/product/edit/" + sanPhamManageDTO.getSanPhamId();
	}

	@PostMapping("genProductDetails")
	public String genProductDetails(ModelMap model, @ModelAttribute("sanPhamManageDTO") SanPhamManageDTO data,
									HttpServletRequest request, RedirectAttributes redirect) {
		String[] kichCoIdsInProductDetail = request.getParameterValues("kichCoIdsInProductDetail");
		String[] mauSacIdsInProductDetail = request.getParameterValues("mauSacIdsInProductDetail");
		String[] soLuongInProductDetail = request.getParameterValues("soLuongInProductDetail");
		String[] giaInProductDetail = request.getParameterValues("giaInProductDetail");
		int countDuplicate = 0;
		int countNotDuplicate = 0;
		if (kichCoIdsInProductDetail != null && mauSacIdsInProductDetail != null && soLuongInProductDetail != null &&  giaInProductDetail != null) {
			Optional<SanPham> optSP = sanPhamService.findById(data.getSanPhamId());
			if (optSP.isPresent()) {
				optSP.get().setDaXoa(false);
				sanPhamService.save(optSP.get());
				for (String kichCoId : kichCoIdsInProductDetail) {
					if(!kichCoId.isEmpty()) {
						for (String mauSacId : mauSacIdsInProductDetail) {
							if(!mauSacId.isEmpty()) {
								int countSPCTDuplicate = sanPhamChiTietService.selectCountChiTietSanPhamDuplicate(
										Integer.parseInt(mauSacId), Integer.parseInt(kichCoId), data.getSanPhamId());
								if (countSPCTDuplicate == 0) {
									ChiTietSanPham spct = new ChiTietSanPham();
									spct.setCoHienThi(true);
									spct.setDaXoa(false);
									spct.setSanPham(optSP.get());
									if(!soLuongInProductDetail[0].isEmpty()) {
										int soLuong = Integer.parseInt(soLuongInProductDetail[0]);
										spct.setSoLuong(soLuong);
									}else {
										redirect.addFlashAttribute("messageDanger", "Số lượng không được để trống");
										return "redirect:/admin/product/edit/" + data.getSanPhamId();
									}
									if(!giaInProductDetail[0].isEmpty()) {
										try {
											BigDecimal gia = new BigDecimal(giaInProductDetail[0]);
											spct.setGia(gia);
										} catch (NumberFormatException e) {
											redirect.addFlashAttribute("messageDanger", "Giá không hợp lệ");
											return "redirect:/admin/product/edit/" + data.getSanPhamId();
										}
									}else {
										redirect.addFlashAttribute("messageDanger", "Giá không được để trống");
										return "redirect:/admin/product/edit/" + data.getSanPhamId();
									}
									KichCo kichCo = new KichCo();
									kichCo.setIdKichCo(Integer.parseInt(kichCoId));
									spct.setKichCo(kichCo);

									MauSac mauSac = new MauSac();
									mauSac.setIdMauSac(Integer.parseInt(mauSacId));
									spct.setMauSac(mauSac);


									DayGiay dayGiay = new DayGiay();
									dayGiay.setIdDayGiay(data.getDayGiayId());
									spct.setDayGiay(dayGiay);

									DeGiay deGiay = new DeGiay();
									deGiay.setIdDeGiay(data.getDeGiayId());
									spct.setDeGiay(deGiay);

									LotGiay lotGiay = new LotGiay();
									lotGiay.setIdLotGiay(data.getLotGiayId());
									spct.setLotGiay(lotGiay);


									sanPhamChiTietService.save(spct);
									countNotDuplicate++;
								}else {
									countDuplicate++;
								}
							}else {
								redirect.addFlashAttribute("messageDanger", "Màu sắc không được để trống");
								return "redirect:/admin/product/edit/" + data.getSanPhamId();
							}
						}
					}else {
						redirect.addFlashAttribute("messageDanger", "Kích cỡ không được để trống");
						return "redirect:/admin/product/edit/" + data.getSanPhamId();
					}
				}
			}
		}else {
			redirect.addFlashAttribute("messageDanger", "Thêm sản phẩm chi tiết thất bại");
			return "redirect:/admin/product/edit/" + data.getSanPhamId();
		}
		if(countDuplicate>0) {
			redirect.addFlashAttribute("messageDanger", "Có "+countDuplicate+" Sản phẩm chi tiết đã tồn tại");
		}
		if(countNotDuplicate>0) {
			redirect.addFlashAttribute("messageSuccess", "Thêm "+countNotDuplicate+" sản phẩm chi tiết thành công");
		}
		return "redirect:/admin/product/edit/"+data.getSanPhamId();
	}

	@GetMapping("deleteProductDetail/{id}")
	public String deleteProductDetail(@PathVariable("id") Integer sanPhamChiTietId, ModelMap model,
									  final RedirectAttributes redirect, @ModelAttribute("sanPhamManageDTO") SanPhamManageDTO sanPhamManageDTO)
			throws IOException {
		Optional<ChiTietSanPham> opt = sanPhamChiTietService.findById(sanPhamChiTietId);
		boolean idSuccess = false;
		if (opt.isPresent()) {
			sanPhamChiTietService.delete(opt.get());
			idSuccess = true;
			if(sanPhamManageDTO.getLstHinhAnhMauSacDTO() != null) {
				for (HinhAnhMauSacDTO hinhAnhMauSacDTO : sanPhamManageDTO.getLstHinhAnhMauSacDTO()) {
					int countHinhAnh = sanPhamChiTietService.getCountChiTietSanPhamExistBySanPhamIdAndMauSacId(
							sanPhamManageDTO.getSanPhamId(), hinhAnhMauSacDTO.getMauSacAddImagesId());
					if (countHinhAnh == 0) {
						List<HinhAnh> lstHA = hinhAnhService.getHinhAnhBySanPhamIdAndMauSacId(
								sanPhamManageDTO.getSanPhamId(), hinhAnhMauSacDTO.getMauSacAddImagesId());
						for (HinhAnh hinhAnh : lstHA) {
							if (!hinhAnh.getTenAnh().isEmpty()) {
								storageService.delete(hinhAnh.getTenAnh());
								hinhAnhService.delete(hinhAnh);
							} else {
								idSuccess = false;
								redirect.addFlashAttribute("messageDanger",
										"Tên hình ảnh: '" + hinhAnh.getTenAnh() + "' không tồn tại");
							}
						}
					}
				}
			}
			int countSPCTExist = sanPhamChiTietService
					.getCountChiTietSanPhamExistBySanPhamId(sanPhamManageDTO.getSanPhamId());
			if (countSPCTExist == 0) {
				Optional<SanPham> optSP = sanPhamService.findById(sanPhamManageDTO.getSanPhamId());
				sanPhamService.delete(optSP.get());
			}
		}
		redirect.addFlashAttribute("sanPhamManageDTO", sanPhamManageDTO);
		if(idSuccess == true) {
			redirect.addFlashAttribute("messageSuccess", "Xóa sản phẩm chi tiết thành công");
			return "redirect:/admin/product/edit/" + sanPhamManageDTO.getSanPhamId();
		}else {
			redirect.addFlashAttribute("messageDanger", "Xóa sản phẩm chi tiết thất bại");
			return "redirect:/admin/product/edit/" + sanPhamManageDTO.getSanPhamId();
		}
	}

	@PostMapping("updateOrCreateProductDetail")
	public String updateProductDetail(@Valid @ModelAttribute("sanPhamChiTietDTO") SanPhamChiTietDTO sanPhamChiTietDTO,
									  BindingResult result, Model model) {
		Optional<ChiTietSanPham> optSPCTOld = sanPhamChiTietService.findById(sanPhamChiTietDTO.getSanPhamId());
		final String messageSuccess = "Cập nhật sản phẩm chi tiết thành công";
		final String messageDanger ="Cập nhật sản phẩm chi tiết thất bại";
		if(optSPCTOld.isPresent()) {
			if(optSPCTOld.get().getKichCo().getIdKichCo().equals(sanPhamChiTietDTO.getKichCoId()) &&
					optSPCTOld.get().getMauSac().getIdMauSac().equals(sanPhamChiTietDTO.getMauSacId()) &&
					optSPCTOld.get().getSanPham().getIdSanPham().equals(sanPhamChiTietDTO.getSanPhamId()) ) {
				optSPCTOld.get().setCoHienThi(sanPhamChiTietDTO.getCoHienThi());
				optSPCTOld.get().setSoLuong(sanPhamChiTietDTO.getSoLuong());
				optSPCTOld.get().setGia(sanPhamChiTietDTO.getGia());
				sanPhamChiTietService.save(optSPCTOld.get());
				model.addAttribute("messageSuccess", messageSuccess);
				model.addAttribute("sanPhamChiTietDTO", sanPhamChiTietDTO);
				return "admin/product/editProductDetail";
			}else {
				Optional<ChiTietSanPham> optSPCT = sanPhamChiTietService.selectChiTietSanPhamDuplicate(
						sanPhamChiTietDTO.getMauSacId(), sanPhamChiTietDTO.getKichCoId(), sanPhamChiTietDTO.getSanPhamId());
				if (optSPCT.isPresent()) {
					sanPhamChiTietService.delete(optSPCTOld.get());
					optSPCT.get().setCoHienThi(sanPhamChiTietDTO.getCoHienThi());
					optSPCT.get().setSoLuong(sanPhamChiTietDTO.getSoLuong());
					optSPCT.get().setGia(sanPhamChiTietDTO.getGia());

					Optional<KichCo> optKC = kichCoService.findById(sanPhamChiTietDTO.getKichCoId());
					if (optKC.isPresent()) {
						optSPCT.get().setKichCo(optKC.get());
					} else {
						model.addAttribute("messageDanger", messageSuccess);
						model.addAttribute("sanPhamChiTietDTO", sanPhamChiTietDTO);
						return "admin/product/editProductDetail";
					}

					Optional<MauSac> optMS = mauSacService.findById(sanPhamChiTietDTO.getMauSacId());
					if (optMS.isPresent()) {
						optSPCT.get().setMauSac(optMS.get());
					} else {
						model.addAttribute("messageDanger", messageDanger);
						model.addAttribute("sanPhamChiTietDTO", sanPhamChiTietDTO);
						return "admin/product/editProductDetail";
					}

					sanPhamChiTietService.save(optSPCT.get());
					model.addAttribute("messageSuccess", messageSuccess);
					model.addAttribute("sanPhamChiTietDTO", sanPhamChiTietDTO);
					return "admin/product/editProductDetail";
				} else {
					optSPCTOld.get().setCoHienThi(sanPhamChiTietDTO.getCoHienThi());
					optSPCTOld.get().setSoLuong(sanPhamChiTietDTO.getSoLuong());
					optSPCT.get().setGia(sanPhamChiTietDTO.getGia());
					Optional<KichCo> optKC = kichCoService.findById(sanPhamChiTietDTO.getKichCoId());
					if (optKC.isPresent()) {
						optSPCTOld.get().setKichCo(optKC.get());
					} else {
						model.addAttribute("messageDanger", messageDanger);
						model.addAttribute("sanPhamChiTietDTO", sanPhamChiTietDTO);
						return "admin/product/editProductDetail";
					}

					Optional<MauSac> optMS = mauSacService.findById(sanPhamChiTietDTO.getMauSacId());
					if (optMS.isPresent()) {
						optSPCTOld.get().setMauSac(optMS.get());
					} else {
						model.addAttribute("messageDanger", messageDanger);
						model.addAttribute("sanPhamChiTietDTO", sanPhamChiTietDTO);
						return "admin/product/editProductDetail";
					}
					Optional<SanPham> optSP = sanPhamService.findById(sanPhamChiTietDTO.getSanPhamId());
					if (optSP.isPresent()) {
						optSPCTOld.get().setSanPham(optSP.get());
					} else {
						model.addAttribute("messageDanger", "Cập nhật sản phẩm chi tiết thất bại");
						model.addAttribute("sanPhamChiTietDTO", sanPhamChiTietDTO);
						return "admin/product/editProductDetail";
					}

					sanPhamChiTietService.save(optSPCTOld.get());

					model.addAttribute("messageSuccess", "Cập nhật sản phẩm chi tiết thành công");
					model.addAttribute("sanPhamChiTietDTO", sanPhamChiTietDTO);
					return "admin/product/editProductDetail";
				}
			}
		}else {
			model.addAttribute("sanPhamChiTietDTO", sanPhamChiTietDTO);
			model.addAttribute("messageDanger", "Cập nhật sản phẩm chi tiết thất bại");
			return "admin/product/editProductDetail";
		}
	}

	@GetMapping("exportExcelProduct")
	public String exportExcelProduct(@ModelAttribute("SPAndSPCTSearchDto") SPAndSPCTSearchDto dataSearch,
									 Optional<Integer> page, Optional<Integer> size, final RedirectAttributes redirect){
		int currentPage = page.orElse(1);
		int pageSize = size.orElse(10);
		redirect.addFlashAttribute("SPAndSPCTSearchDto", dataSearch);
		redirect.addFlashAttribute("page", currentPage);
		redirect.addFlashAttribute("size", pageSize);
		Pageable pageable = PageRequest.of(currentPage - 1, pageSize);
		Page<SanPham> resultPage = null;
		Optional<SPAndSPCTSearchDto> optDataSearch = Optional.of(dataSearch);
		List<SanPhamProductManageDTO> lstDto = new ArrayList<>();
		if (optDataSearch.isPresent()) {
			resultPage = sanPhamService.searchProductExist(dataSearch, pageable);
			if(resultPage.hasContent()) {
				for (SanPham sanPham : resultPage.getContent()) {
					int countSPCT = sanPhamChiTietService.getCountChiTietSanPhamExistBySanPhamId(sanPham.getIdSanPham());
					List<HinhAnh> lstHA = hinhAnhService.getHinhAnhChinhBySanPhamId(sanPham.getIdSanPham());
					List<String> lstHAStr = new ArrayList<>();
					for (HinhAnh hinhAnh : lstHA) {
						lstHAStr.add(hinhAnh.getTenAnh());
					}
					SanPhamProductManageDTO spMN = new SanPhamProductManageDTO();
					spMN.setSanPham(sanPham);
					spMN.setAnhChinhs(lstHAStr);
					spMN.setTongSoLuong(countSPCT);
					lstDto.add(spMN);
				}
			}else {
				redirect.addFlashAttribute("messageDanger", "Không có dữ liệu để export");
				return "redirect:/admin/product";
			}
		}
		boolean checked = false;
		try {
			sanPhamChiTietService.WritingToExcelProduct(lstDto);
		} catch (IOException e) {
			checked = true;
			e.printStackTrace();
		}
		if(!checked) {
			redirect.addFlashAttribute("messageSuccess", "Export excel thành công");
		}else {
			redirect.addFlashAttribute("messageDanger", "Export excel thất bại");
		}
		return "redirect:/admin/product";
	}


	public void showViewBeforeSearch(ModelMap model, SPAndSPCTSearchDto dataSearch, Optional<Integer> page,
									 Optional<Integer> size) {
		int currentPage = page.orElse(1);
		int pageSize = size.orElse(10);

		Pageable pageable = PageRequest.of(currentPage - 1, pageSize);
		Page<SanPham> resultPage = null;
		Optional<SPAndSPCTSearchDto> optDataSearch = Optional.of(dataSearch);
		if (optDataSearch.isPresent()) {
			resultPage = sanPhamService.searchProductExist(dataSearch, pageable);
			model.addAttribute("dataSearch", dataSearch);
		}

		int totalPages = resultPage.getTotalPages();
		if (totalPages > 0) {
			int start = Math.max(1, currentPage - 2);
			int end = Math.min(currentPage + 2, totalPages);
			if (totalPages > 5) {
				if (end == totalPages) {
					start = end - 5;
				} else if (start == 1) {
					end = start + 5;
				}
			}
			List<Integer> pageNumbers = IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		}
		model.addAttribute("sanPhamPage", resultPage);
	}

	public static boolean isNumeric(String str) {
		return str.matches("-?\\d+(\\.\\d+)?");
	}

}
