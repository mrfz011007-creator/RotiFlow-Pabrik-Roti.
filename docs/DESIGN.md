# RotiFlow — Dokumen Desain yang Disepakati

**Versi:** 0.2  
**Status:** Checkpoint desain — berdasarkan diskusi dengan pengguna  
**Tujuan:** Menjaga keputusan desain yang sudah disepakati agar dapat dilanjutkan tanpa mengandalkan ingatan percakapan saja.  
**Penting:** Ini adalah rancangan prototipe, belum implementasi aplikasi. Hal yang belum diputuskan ditandai secara eksplisit dan tidak boleh diasumsikan.

## 1. Evaluasi arah produk

RotiFlow ditujukan untuk membantu pabrik roti dengan model bisnis **B2B**. Bos menerima pesanan dari banyak pedagang, lalu memproduksi roti berdasarkan pesanan tersebut. Jumlah pesanan tiap pedagang berbeda.

Fokus awal yang disepakati adalah **pencatatan pesanan harian per pedagang dan rekap otomatis kebutuhan roti**. Bukan pencatatan produksi aktual atau sistem stok.

Alasan desain:
- Pesanan perlu dicatat per pedagang satu per satu.
- Pedagang yang dimasukkan hanya pedagang yang berjualan/memesan pada hari itu; aplikasi tidak perlu meminta pengguna memastikan semua pedagang terdaftar setiap hari.
- Pesanan harian dimulai dari kosong. Tidak perlu fitur menyalin pesanan kemarin untuk versi awal.
- Bos biasanya memproduksi lebih untuk mengantisipasi risiko roti rusak selama produksi. Karena itu, jumlah produksi aktual tidak perlu dicatat dalam ruang lingkup awal.
- Tampilan harus praktis untuk banyak pedagang dan input berulang.

## 2. Alur pesanan harian

1. Pengguna membuka pencatatan pesanan untuk suatu tanggal.
2. Pengguna memilih nama pedagang yang pernah dimasukkan atau menambahkan nama pedagang baru.
3. Pengguna mengisi jumlah pesanan untuk 15 jenis roti pada tabel ringkas.
4. Pengguna menyimpan pesanan pedagang.
5. Pengguna mengulangi proses untuk pedagang lain yang memesan pada tanggal tersebut.
6. Aplikasi menampilkan daftar pesanan per pedagang dan menghitung total per jenis roti secara otomatis.
7. Pesanan yang sudah tersimpan dapat diedit kapan saja, termasuk pesanan tanggal sebelumnya.

Nama pedagang yang pernah dimasukkan disimpan agar dapat dipilih kembali. Daftar nama yang tersimpan tidak berarti semua pedagang harus dimasukkan setiap hari.

## 3. Daftar jenis roti tetap

Daftar awal berisi 15 jenis berikut. Ejaan dipertahankan sesuai informasi pengguna:

1. Srikaya
2. Kelapa
3. Paha ayam
4. Donat buat
5. Moca
6. Abon
7. Keju
8. Mantul
9. Donat panjang
10. Kacang hijau
11. Pisang coklat
12. Seres warna
13. Cappucino
14. Nanas
15. Manis

Jenis roti ditampilkan sebagai pilihan tetap, bukan diketik ulang saat mencatat pesanan.

## 4. Aturan input jumlah

- Setiap jenis roti memiliki kolom jumlah di sisi kanan nama roti.
- Input hanya menerima **angka bulat nonnegatif**.
- Huruf, tanda minus, desimal, dan simbol lain tidak boleh diterima.
- Jumlah kosong diperlakukan sebagai 0 saat menyimpan.
- Nilai jumlah menggunakan satuan buah.
- Aplikasi menghitung jumlah keseluruhan pesanan pedagang dan total per jenis roti secara otomatis.

Catatan implementasi: gunakan papan ketik numerik pada Android dan validasi data juga di sisi aplikasi; jangan hanya mengandalkan tampilan papan ketik.

## 5. Tampilan daftar pesanan

### Daftar utama per tanggal
- Utamakan daftar/tabel ringkas, bukan kartu besar untuk setiap pedagang.
- Setiap baris menampilkan nama pedagang dan total jumlah roti yang dipesan.
- Baris dapat dibuka untuk melihat rincian 15 jenis roti.
- Daftar pesanan per pedagang menjadi fokus utama.

### Total keseluruhan
- Total pesanan untuk masing-masing dari 15 jenis roti ditampilkan di bagian bawah daftar pesanan.
- Untuk tahap awal tidak perlu halaman rekap total terpisah.

### Arah visual
Arah visual yang dipilih sebelumnya adalah **terang dan profesional**:
- Latar terang/netral.
- Teks berkontras tinggi.
- Kartu dan tabel sederhana dengan jarak yang nyaman.
- Aksen warna seperlunya, tanpa dekorasi berlebihan.
- Tombol dan kolom input mudah digunakan pada layar HP.

## 6. Edit dan riwayat

- Pesanan yang telah disimpan dapat diedit kapan saja, termasuk tanggal sebelumnya.
- Mengedit pesanan harus memperbarui total pedagang dan total per jenis roti untuk tanggal terkait.
- Perubahan pada pesanan lama tidak boleh mengubah tanggal pesanan secara diam-diam.
- Detail perilaku penghapusan pesanan belum diputuskan.
- Mekanisme cadangan/pemulihan data belum diputuskan.

## 7. Di luar ruang lingkup versi awal

Fitur berikut **belum disepakati** dan tidak boleh ditambahkan sebagai asumsi:
- Pencatatan jumlah produksi aktual.
- Stok fisik lintas hari.
- Pencatatan roti rusak, retur, atau roti sisa.
- Keuangan, pembayaran, utang, atau laporan akuntansi.
- Resep dan bahan baku.
- Login, sinkronisasi cloud, atau penggunaan banyak perangkat.
- Menyalin pesanan hari sebelumnya.
- Penghapusan permanen atau arsip nama pedagang.

## 8. Kriteria awal prototipe

- Dapat dipasang dan dibuka di HP Android.
- Pengguna dapat memilih/menambahkan nama pedagang.
- Pengguna dapat mencatat pesanan dengan 15 jenis roti.
- Kolom jumlah hanya menerima angka bulat nonnegatif.
- Daftar per tanggal menampilkan nama pedagang dan total pesanannya.
- Rincian pesanan pedagang dapat dibuka dan diedit.
- Total per jenis roti diperbarui sesuai data yang tersimpan.
- Data tetap tersedia setelah aplikasi ditutup dan dibuka kembali, jika penyimpanan lokal telah diimplementasikan.
- Data contoh, jika digunakan, harus diberi label DEMO dan tidak boleh dianggap sebagai data nyata pabrik.

## 9. Hal yang masih perlu dikonfirmasi

Pertanyaan berikut belum diputuskan; jangan menetapkan jawabannya tanpa bertanya kepada pengguna.

1. **Tanggal:** apakah aplikasi otomatis membuka tanggal hari ini dan mengizinkan pengguna mengganti tanggal, atau tanggal harus dipilih secara manual setiap kali?
2. **Penghapusan:** apakah pesanan yang salah boleh dihapus, atau cukup diedit?
3. **Penyimpanan:** apakah versi demo cukup menyimpan data di satu HP secara lokal?
4. **Identitas pedagang:** apakah nama saja cukup untuk membedakan pedagang, atau perlu kolom keterangan lain?

## 10. Checkpoint pengerjaan

**Checkpoint saat ini: desain dan alur bisnis sedang dikunci. Belum mulai implementasi Android.**

Keputusan yang sudah disepakati harus dipertahankan saat memperbarui `docs/WIREFRAME.md`, roadmap, dan implementasi berikutnya. Jika ada konflik dengan dokumen lama yang menyebut pencatatan produksi/distribusi aktual, dokumen desain ini menjadi acuan terbaru untuk ruang lingkup awal.

Langkah berikutnya: lanjutkan pertanyaan yang belum diputuskan satu per satu, lalu sinkronkan wireframe dan roadmap sebelum mulai menulis kode aplikasi.
