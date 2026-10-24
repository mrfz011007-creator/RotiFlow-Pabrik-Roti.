# RotiFlow — Roadmap

**Status:** Desain alur bisnis dikunci untuk ditinjau; implementasi belum dimulai.  
**Prinsip:** Jangan mulai coding sebelum dokumen desain dan wireframe selaras dan keputusan penting yang belum jelas sudah dikonfirmasi.

## Tahap 0 — Desain dan validasi

- [x] Membuat repo terpisah untuk RotiFlow.
- [x] Membuat dokumen desain, roadmap, dan wireframe awal.
- [x] Menetapkan fokus versi awal: pesanan harian B2B per pedagang.
- [x] Menetapkan daftar 15 jenis roti.
- [x] Menetapkan input tabel ringkas dengan angka bulat nonnegatif saja.
- [x] Menetapkan nama pedagang disimpan untuk dipilih kembali; pedagang yang dimasukkan hanya yang memesan hari itu.
- [x] Menetapkan daftar pedagang sebagai tampilan utama dan total per jenis di bawah daftar.
- [x] Menetapkan tanggal default hari ini, dengan pilihan tanggal lain.
- [x] Menetapkan edit dan hapus pesanan; hapus harus dikonfirmasi.
- [x] Menetapkan penyimpanan lokal di satu HP untuk prototipe awal.
- [ ] Tinjau dokumen desain dan wireframe terbaru bersama pengguna.
- [x] Memperbolehkan beberapa pesanan terpisah dari pedagang yang sama pada tanggal yang sama; semua pesanan dihitung dalam rekap.
- [ ] Konfirmasi cara cadangan/pemulihan data atau putuskan bahwa fitur itu di luar prototipe awal.

## Tahap 1 — Fondasi Android dan build otomatis

- [ ] Buat proyek Android native.
- [ ] Tambahkan Gradle Wrapper yang sesuai.
- [ ] Tambahkan GitHub Actions untuk build APK debug pada push dan workflow manual.
- [ ] Pastikan build gagal terlihat jelas di log dan APK tersedia sebagai artifact.
- [ ] Verifikasi workflow berhasil sebelum implementasi fitur utama berlanjut.

## Tahap 2 — Fitur prototipe pesanan

- [ ] Buat penyimpanan lokal untuk nama pedagang dan pesanan bertanggal.
- [ ] Buat daftar nama pedagang tersimpan dan tambah nama baru.
- [ ] Buat form pesanan dengan 15 jenis roti tetap.
- [ ] Batasi input kuantitas ke bilangan bulat nonnegatif; kolom kosong disimpan sebagai 0.
- [ ] Buat daftar pesanan per tanggal, dengan nama pedagang dan total roti per catatan pesanan; pedagang yang sama dapat muncul beberapa kali.
- [ ] Buat tampilan rincian pesanan.
- [ ] Buat total per jenis roti di bawah daftar pedagang.
- [ ] Buat pilihan tanggal; default hari ini.
- [ ] Buat edit pesanan lama.
- [ ] Buat hapus dengan dialog konfirmasi.
- [ ] Pastikan perubahan pesanan menghitung ulang semua total terkait.

## Tahap 3 — Uji dan verifikasi

- [ ] Uji tambah pesanan untuk beberapa pedagang, termasuk beberapa catatan terpisah untuk pedagang yang sama pada tanggal yang sama.
- [ ] Uji perhitungan total per pedagang dan per jenis roti.
- [ ] Uji input huruf, angka negatif, desimal, simbol, dan kolom kosong.
- [ ] Uji perubahan tanggal, edit pesanan lama, dan penghapusan dengan konfirmasi.
- [ ] Tutup dan buka ulang aplikasi untuk memastikan data lokal bertahan.
- [ ] Periksa log GitHub Actions dan pasang APK pada perangkat Android untuk verifikasi pengguna.

## Tahap 4 — Demo dan evaluasi bisnis

- [ ] Beri label jelas bahwa APK dan data adalah demo.
- [ ] Tunjukkan prototipe kepada bos dan catat masukan.
- [ ] Jangan menambahkan pencatatan produksi aktual, stok, kerusakan, retur, keuangan, login, atau sinkronisasi sebelum kebutuhan dikonfirmasi.
- [ ] Perbarui desain dan roadmap dari hasil demo sebelum menambah ruang lingkup.

## Di luar ruang lingkup prototipe awal

- Pencatatan produksi aktual.
- Stok fisik lintas hari, roti rusak, sisa, dan retur.
- Keuangan, pembayaran, utang, bahan baku, resep, dan laporan akuntansi.
- Login, sinkronisasi cloud, dan akses multi-perangkat.
- Menyalin pesanan hari sebelumnya.

Lihat [DESIGN.md](DESIGN.md) untuk keputusan bisnis dan [WIREFRAME.md](WIREFRAME.md) untuk susunan layar.
