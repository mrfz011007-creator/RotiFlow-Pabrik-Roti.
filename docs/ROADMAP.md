# RotiFlow — Roadmap

**Status:** Implementasi fitur pesanan awal sudah ditambahkan ke aplikasi Android native. Build CI dan uji perangkat masih harus diverifikasi.
**Prinsip:** Ruang lingkup mengikuti [DESIGN.md](DESIGN.md); fitur di luar ruang lingkup tidak ditambahkan tanpa persetujuan.

## Tahap 0 — Desain dan validasi

- [x] Membuat repo terpisah untuk RotiFlow.
- [x] Membuat dokumen desain, roadmap, dan wireframe.
- [x] Menetapkan fokus versi awal: pesanan harian B2B per pedagang.
- [x] Menetapkan daftar 15 jenis roti, input bilangan bulat nonnegatif, dan kolom kosong sebagai 0.
- [x] Menetapkan nama pedagang tersimpan, tanggal default hari ini, riwayat tanggal lama, edit dan hapus dengan konfirmasi.
- [x] Menyetujui penyimpanan lokal dan penundaan backup/restore.
- [x] Memperbolehkan pesanan terpisah untuk pedagang yang sama pada tanggal yang sama.

## Tahap 1 — Fondasi Android dan build otomatis

- [x] Membuat proyek Android native.
- [x] Mengonfigurasi GitHub Actions untuk build APK debug pada push dan workflow manual.
- [x] Menghasilkan APK sebagai artifact dan menyediakan unduhan langsung melalui GitHub Releases.
- [ ] Menambahkan Gradle Wrapper ke repositori agar tersedia juga untuk build lokal tanpa harus dibuat oleh workflow.
- [ ] Memastikan build dari implementasi fitur terbaru berhasil di GitHub Actions.

## Tahap 2 — Fitur prototipe pesanan

- [x] Penyimpanan lokal SQLite untuk nama pedagang dan pesanan bertanggal.
- [x] Memilih nama pedagang tersimpan atau mengetik nama baru.
- [x] Form pesanan berisi 15 jenis roti tetap.
- [x] Input angka bulat nonnegatif; kolom kosong disimpan sebagai 0; total form diperbarui otomatis.
- [x] Daftar pesanan per tanggal, nama pedagang, dan total untuk tiap catatan pesanan.
- [x] Rincian pesanan per pedagang.
- [x] Total per jenis roti ditampilkan di bawah daftar pedagang.
- [x] Pilih tanggal; default hari ini.
- [x] Riwayat tanggal lama dan keadaan kosong saat belum ada pesanan.
- [x] Edit pesanan lama tanpa mengubah tanggal secara diam-diam.
- [x] Hapus pesanan dengan dialog konfirmasi.
- [x] Total terkait dihitung ulang berdasarkan data yang tersimpan.

## Tahap 3 — Uji dan verifikasi (belum dinyatakan lulus)

- [ ] Verifikasi build GitHub Actions setelah perubahan fitur terakhir.
- [ ] Uji tambah pesanan untuk beberapa pedagang, termasuk beberapa catatan terpisah dari pedagang yang sama di tanggal yang sama.
- [ ] Periksa total per pedagang dan per jenis roti secara manual.
- [ ] Uji huruf, angka negatif, desimal, simbol, angka sangat besar, dan kolom kosong.
- [ ] Uji perubahan tanggal, edit pesanan lama, dan penghapusan dengan konfirmasi.
- [ ] Tutup dan buka ulang aplikasi untuk memastikan data lokal tetap tersedia.
- [ ] Periksa log build dan uji APK terbaru pada perangkat Android.
- [ ] Periksa layout pada ukuran layar Android yang berbeda.

## Tahap 4 — Demo dan evaluasi bisnis

- [ ] Validasi prototipe dengan calon pengguna dan catat masukan.
- [ ] Pertimbangkan backup/restore sebelum aplikasi menjadi catatan operasional.
- [ ] Jangan menambahkan pencatatan produksi, stok, kerusakan, retur, keuangan, login, atau sinkronisasi sebelum kebutuhannya dikonfirmasi.

## Di luar ruang lingkup prototipe awal

Pencatatan produksi aktual; stok fisik lintas hari; roti rusak, sisa, dan retur; keuangan, pembayaran, utang, bahan baku, resep, laporan akuntansi; login, sinkronisasi cloud, multi-perangkat; menyalin pesanan hari sebelumnya.

Lihat [DESIGN.md](DESIGN.md) dan [WIREFRAME.md](WIREFRAME.md) untuk spesifikasi utama.
