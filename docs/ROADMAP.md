# RotiFlow — Roadmap

## Tahap 0 — Desain
- [x] Menentukan tujuan prototipe produksi dan distribusi.
- [x] Mendefinisikan fitur, layar, aturan data, dan kriteria demo.
- [x] Menyiapkan dokumen desain dan roadmap di GitHub.
- [x] Membuat wireframe awal layar Ringkasan, Produksi, Distribusi, dan Riwayat.
- [ ] Membahas dan menyepakati arah visual serta keputusan alur dengan pengguna.
- [ ] Memperbarui desain berdasarkan hasil diskusi sebelum implementasi UI.

## Tahap 1 — Kerangka proyek
- [ ] Siapkan proyek Android native.
- [ ] Pastikan Gradle Wrapper tersedia.
- [ ] Buat GitHub Actions untuk build APK debug.
- [ ] Pastikan workflow menghasilkan artefak APK yang dapat diunduh.

## Tahap 2 — Prototipe
- [ ] Dashboard.
- [ ] Catatan produksi.
- [ ] Catatan distribusi per pedagang.
- [ ] Riwayat dan filter tanggal sederhana.
- [ ] Validasi input dan penyimpanan lokal.
- [ ] Data demo terpisah jelas dari data nyata.

## Tahap 3 — Verifikasi
- [ ] Build berhasil di GitHub Actions.
- [ ] Uji input dan perhitungan ringkasan.
- [ ] Uji data setelah aplikasi dibuka ulang.
- [ ] Uji input kosong/tidak valid dan navigasi.
- [ ] Instal APK di HP pengguna.

## Tahap 4 — Demo dan validasi bisnis
- [ ] Demo kepada pemilik pabrik.
- [ ] Catat masukan dan prioritas masalah.
- [ ] Tentukan kebutuhan multi-perangkat, backup, login, dan laporan.
- [ ] Bahas ruang lingkup, biaya, kepemilikan, dukungan, dan persetujuan sebelum operasional.

## Prinsip kerja
- Repo ini terpisah; jangan mengubah My Bread atau RotiKu.
- Jangan menyatakan fitur selesai sebelum diuji.
- APK demo bukan catatan operasional tunggal.
- Perubahan desain besar mengikuti masukan pengguna/pabrik.
