# RotiFlow — Desain Prototipe Produksi & Distribusi Roti

**Status:** Draft desain v0.1 — untuk didiskusikan dengan pemilik pabrik  
**Tujuan demo:** Menunjukkan gambaran pencatatan produksi dan pengiriman roti melalui aplikasi Android.  
**Batasan:** Belum untuk operasional nyata sebelum validasi, pengujian, dan persetujuan pabrik.

## 1. Tujuan
Aplikasi awal membantu menjawab:
1. Berapa jumlah roti yang diproduksi pada tanggal tertentu?
2. Berapa jumlah roti yang dikirim kepada masing-masing pedagang?
3. Berapa selisih catatan produksi dan distribusi pada periode tersebut?

Versi awal tidak mengasumsikan proses retur, kerusakan, penjualan, uang, bahan baku, atau stok fisik karena alur itu belum divalidasi.

## 2. Pengguna
- **Pemilik/atasan:** melihat ringkasan produksi dan distribusi.
- **Petugas produksi:** mencatat hasil produksi.
- **Petugas distribusi:** mencatat pengiriman per pedagang.

Prototipe awal tidak memiliki akun, peran pengguna, atau sinkronisasi antarperangkat.

## 3. Fitur prototipe
- Dashboard ringkas.
- Tambah dan lihat catatan produksi.
- Tambah dan lihat catatan distribusi per pedagang.
- Tanggal, jenis roti, jumlah dalam satuan buah.
- Riwayat catatan.
- Validasi jumlah agar tidak kosong atau negatif.
- Data contoh berlabel **DEMO**.
- Penyimpanan lokal pada satu HP jika sudah diimplementasikan dan diuji.

### Di luar ruang lingkup
Keuangan, retur, roti rusak/kedaluwarsa, bahan baku/resep, stok fisik lintas hari, login, sinkronisasi cloud, multi-HP, dan laporan akuntansi. Fitur tersebut harus dibahas dengan pihak pabrik terlebih dahulu.

## 4. Layar
### Dashboard
Menampilkan tanggal dan:
- **Produksi tercatat** — total buah diproduksi pada tanggal itu.
- **Distribusi tercatat** — total buah dikirim pada tanggal itu.
- **Selisih catatan** — produksi hari itu dikurangi distribusi hari itu.

Selisih ini bukan stok fisik karena stok awal, retur, kerusakan, dan penyesuaian belum dicatat.

### Produksi
Form: tanggal produksi, jenis/nama roti, jumlah diproduksi (buah), catatan opsional, tombol Simpan Produksi. Tampilkan riwayat produksi.

### Distribusi
Form: tanggal pengiriman, nama pedagang/pelanggan, jenis/nama roti, jumlah dikirim (buah), catatan opsional, tombol Simpan Distribusi. Tampilkan riwayat yang bisa difilter menurut tanggal dan pedagang.

### Data demo
Gunakan data fiktif seperti “Pedagang Demo A” dan “Roti Cokelat”. Jangan memasukkan data internal pabrik tanpa izin.

## 5. Navigasi
Dashboard → Produksi → Tambah Produksi → Simpan → Riwayat Produksi  
Dashboard → Distribusi → Tambah Distribusi → Simpan → Riwayat Distribusi

Navigasi utama: **Ringkasan**, **Produksi**, **Distribusi**.

## 6. Arah visual
- Android native, sederhana, mudah dibaca.
- Latar netral terang, teks kontras, kartu ringkasan.
- Warna utama biru tua atau hijau gelap dengan satu warna aksen.
- Tombol besar, label jelas, formulir pendek.
- Bahasa Indonesia dan angka mudah dibaca.
- Hindari animasi berat untuk menjaga kenyamanan pada HP dengan sumber daya terbatas.

## 7. Aturan data awal
- Kuantitas bilangan bulat, satuan **buah**.
- Setiap catatan memiliki ID, tanggal, jenis roti, kuantitas, waktu pencatatan, dan catatan opsional.
- Catatan distribusi juga menyimpan nama pedagang/pelanggan.
- Catatan produksi dan distribusi dipisahkan.
- Perubahan/penghapusan catatan harus memperbarui ringkasan.
- Penyimpanan lokal berarti data hanya ada di perangkat itu dan tidak otomatis tersedia di perangkat lain.

## 8. Kriteria demo
- APK dapat dipasang dan dibuka di HP Android.
- Dashboard menampilkan data demo dengan jelas.
- Pengguna dapat menambah catatan produksi dan distribusi.
- Ringkasan berubah mengikuti data.
- Data bertahan setelah aplikasi dibuka ulang jika penyimpanan lokal telah dibuat.
- Tidak crash saat input kosong/tidak valid atau berpindah halaman.
- Tidak mengklaim kesiapan operasional atau penyimpanan cloud tanpa pengujian.

## 9. Tahapan implementasi
1. Simpan dan tinjau desain.
2. Siapkan kerangka Android native dan Gradle Wrapper.
3. Buat GitHub Actions untuk build APK debug dan unggah artefak.
4. Implementasikan dashboard, formulir, riwayat, validasi, dan penyimpanan lokal.
5. Tambahkan pengujian dasar.
6. Periksa log build dan perbaiki kegagalan.
7. Instal dan uji APK di HP.
8. Demo ke bos, lalu catat masukan sebelum fitur operasional ditambah.

## 10. Pertanyaan untuk bos
- Siapa mencatat produksi dan siapa mencatat distribusi?
- Apakah satu produk memiliki beberapa ukuran/varian?
- Pengiriman dicatat per pedagang, rute, atau kendaraan?
- Apakah distribusi mencakup titipan, penggantian, atau retur?
- Perlu laporan harian, mingguan, atau bulanan?
- Berapa HP perlu menggunakan data yang sama?
- Apa yang boleh diubah/dihapus setelah disimpan?
