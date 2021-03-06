// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: sensorupload.proto

#ifndef PROTOBUF_sensorupload_2eproto__INCLUDED
#define PROTOBUF_sensorupload_2eproto__INCLUDED

#include <string>

#include <google/protobuf/stubs/common.h>

#if GOOGLE_PROTOBUF_VERSION < 2005000
#error This file was generated by a newer version of protoc which is
#error incompatible with your Protocol Buffer headers.  Please update
#error your headers.
#endif
#if 2005000 < GOOGLE_PROTOBUF_MIN_PROTOC_VERSION
#error This file was generated by an older version of protoc which is
#error incompatible with your Protocol Buffer headers.  Please
#error regenerate this file with a newer version of protoc.
#endif

#include <google/protobuf/generated_message_util.h>
#include <google/protobuf/message.h>
#include <google/protobuf/repeated_field.h>
#include <google/protobuf/extension_set.h>
#include <google/protobuf/unknown_field_set.h>
// @@protoc_insertion_point(includes)

namespace nervousproto {

// Internal implementation detail -- do not call these.
void  protobuf_AddDesc_sensorupload_2eproto();
void protobuf_AssignDesc_sensorupload_2eproto();
void protobuf_ShutdownFile_sensorupload_2eproto();

class SensorUpload;
class SensorUpload_SensorData;

// ===================================================================

class SensorUpload_SensorData : public ::google::protobuf::Message {
 public:
  SensorUpload_SensorData();
  virtual ~SensorUpload_SensorData();

  SensorUpload_SensorData(const SensorUpload_SensorData& from);

  inline SensorUpload_SensorData& operator=(const SensorUpload_SensorData& from) {
    CopyFrom(from);
    return *this;
  }

  inline const ::google::protobuf::UnknownFieldSet& unknown_fields() const {
    return _unknown_fields_;
  }

  inline ::google::protobuf::UnknownFieldSet* mutable_unknown_fields() {
    return &_unknown_fields_;
  }

  static const ::google::protobuf::Descriptor* descriptor();
  static const SensorUpload_SensorData& default_instance();

  void Swap(SensorUpload_SensorData* other);

  // implements Message ----------------------------------------------

  SensorUpload_SensorData* New() const;
  void CopyFrom(const ::google::protobuf::Message& from);
  void MergeFrom(const ::google::protobuf::Message& from);
  void CopyFrom(const SensorUpload_SensorData& from);
  void MergeFrom(const SensorUpload_SensorData& from);
  void Clear();
  bool IsInitialized() const;

  int ByteSize() const;
  bool MergePartialFromCodedStream(
      ::google::protobuf::io::CodedInputStream* input);
  void SerializeWithCachedSizes(
      ::google::protobuf::io::CodedOutputStream* output) const;
  ::google::protobuf::uint8* SerializeWithCachedSizesToArray(::google::protobuf::uint8* output) const;
  int GetCachedSize() const { return _cached_size_; }
  private:
  void SharedCtor();
  void SharedDtor();
  void SetCachedSize(int size) const;
  public:

  ::google::protobuf::Metadata GetMetadata() const;

  // nested types ----------------------------------------------------

  // accessors -------------------------------------------------------

  // required uint64 record_time = 1;
  inline bool has_record_time() const;
  inline void clear_record_time();
  static const int kRecordTimeFieldNumber = 1;
  inline ::google::protobuf::uint64 record_time() const;
  inline void set_record_time(::google::protobuf::uint64 value);

  // repeated bool value_bool = 2 [packed = true];
  inline int value_bool_size() const;
  inline void clear_value_bool();
  static const int kValueBoolFieldNumber = 2;
  inline bool value_bool(int index) const;
  inline void set_value_bool(int index, bool value);
  inline void add_value_bool(bool value);
  inline const ::google::protobuf::RepeatedField< bool >&
      value_bool() const;
  inline ::google::protobuf::RepeatedField< bool >*
      mutable_value_bool();

  // repeated int32 value_int32 = 3 [packed = true];
  inline int value_int32_size() const;
  inline void clear_value_int32();
  static const int kValueInt32FieldNumber = 3;
  inline ::google::protobuf::int32 value_int32(int index) const;
  inline void set_value_int32(int index, ::google::protobuf::int32 value);
  inline void add_value_int32(::google::protobuf::int32 value);
  inline const ::google::protobuf::RepeatedField< ::google::protobuf::int32 >&
      value_int32() const;
  inline ::google::protobuf::RepeatedField< ::google::protobuf::int32 >*
      mutable_value_int32();

  // repeated int64 value_int64 = 4 [packed = true];
  inline int value_int64_size() const;
  inline void clear_value_int64();
  static const int kValueInt64FieldNumber = 4;
  inline ::google::protobuf::int64 value_int64(int index) const;
  inline void set_value_int64(int index, ::google::protobuf::int64 value);
  inline void add_value_int64(::google::protobuf::int64 value);
  inline const ::google::protobuf::RepeatedField< ::google::protobuf::int64 >&
      value_int64() const;
  inline ::google::protobuf::RepeatedField< ::google::protobuf::int64 >*
      mutable_value_int64();

  // repeated float value_float = 5 [packed = true];
  inline int value_float_size() const;
  inline void clear_value_float();
  static const int kValueFloatFieldNumber = 5;
  inline float value_float(int index) const;
  inline void set_value_float(int index, float value);
  inline void add_value_float(float value);
  inline const ::google::protobuf::RepeatedField< float >&
      value_float() const;
  inline ::google::protobuf::RepeatedField< float >*
      mutable_value_float();

  // repeated double value_double = 6 [packed = true];
  inline int value_double_size() const;
  inline void clear_value_double();
  static const int kValueDoubleFieldNumber = 6;
  inline double value_double(int index) const;
  inline void set_value_double(int index, double value);
  inline void add_value_double(double value);
  inline const ::google::protobuf::RepeatedField< double >&
      value_double() const;
  inline ::google::protobuf::RepeatedField< double >*
      mutable_value_double();

  // repeated string value_string = 7;
  inline int value_string_size() const;
  inline void clear_value_string();
  static const int kValueStringFieldNumber = 7;
  inline const ::std::string& value_string(int index) const;
  inline ::std::string* mutable_value_string(int index);
  inline void set_value_string(int index, const ::std::string& value);
  inline void set_value_string(int index, const char* value);
  inline void set_value_string(int index, const char* value, size_t size);
  inline ::std::string* add_value_string();
  inline void add_value_string(const ::std::string& value);
  inline void add_value_string(const char* value);
  inline void add_value_string(const char* value, size_t size);
  inline const ::google::protobuf::RepeatedPtrField< ::std::string>& value_string() const;
  inline ::google::protobuf::RepeatedPtrField< ::std::string>* mutable_value_string();

  // @@protoc_insertion_point(class_scope:nervousproto.SensorUpload.SensorData)
 private:
  inline void set_has_record_time();
  inline void clear_has_record_time();

  ::google::protobuf::UnknownFieldSet _unknown_fields_;

  ::google::protobuf::uint64 record_time_;
  ::google::protobuf::RepeatedField< bool > value_bool_;
  mutable int _value_bool_cached_byte_size_;
  ::google::protobuf::RepeatedField< ::google::protobuf::int32 > value_int32_;
  mutable int _value_int32_cached_byte_size_;
  ::google::protobuf::RepeatedField< ::google::protobuf::int64 > value_int64_;
  mutable int _value_int64_cached_byte_size_;
  ::google::protobuf::RepeatedField< float > value_float_;
  mutable int _value_float_cached_byte_size_;
  ::google::protobuf::RepeatedField< double > value_double_;
  mutable int _value_double_cached_byte_size_;
  ::google::protobuf::RepeatedPtrField< ::std::string> value_string_;

  mutable int _cached_size_;
  ::google::protobuf::uint32 _has_bits_[(7 + 31) / 32];

  friend void  protobuf_AddDesc_sensorupload_2eproto();
  friend void protobuf_AssignDesc_sensorupload_2eproto();
  friend void protobuf_ShutdownFile_sensorupload_2eproto();

  void InitAsDefaultInstance();
  static SensorUpload_SensorData* default_instance_;
};
// -------------------------------------------------------------------

class SensorUpload : public ::google::protobuf::Message {
 public:
  SensorUpload();
  virtual ~SensorUpload();

  SensorUpload(const SensorUpload& from);

  inline SensorUpload& operator=(const SensorUpload& from) {
    CopyFrom(from);
    return *this;
  }

  inline const ::google::protobuf::UnknownFieldSet& unknown_fields() const {
    return _unknown_fields_;
  }

  inline ::google::protobuf::UnknownFieldSet* mutable_unknown_fields() {
    return &_unknown_fields_;
  }

  static const ::google::protobuf::Descriptor* descriptor();
  static const SensorUpload& default_instance();

  void Swap(SensorUpload* other);

  // implements Message ----------------------------------------------

  SensorUpload* New() const;
  void CopyFrom(const ::google::protobuf::Message& from);
  void MergeFrom(const ::google::protobuf::Message& from);
  void CopyFrom(const SensorUpload& from);
  void MergeFrom(const SensorUpload& from);
  void Clear();
  bool IsInitialized() const;

  int ByteSize() const;
  bool MergePartialFromCodedStream(
      ::google::protobuf::io::CodedInputStream* input);
  void SerializeWithCachedSizes(
      ::google::protobuf::io::CodedOutputStream* output) const;
  ::google::protobuf::uint8* SerializeWithCachedSizesToArray(::google::protobuf::uint8* output) const;
  int GetCachedSize() const { return _cached_size_; }
  private:
  void SharedCtor();
  void SharedDtor();
  void SetCachedSize(int size) const;
  public:

  ::google::protobuf::Metadata GetMetadata() const;

  // nested types ----------------------------------------------------

  typedef SensorUpload_SensorData SensorData;

  // accessors -------------------------------------------------------

  // required uint64 huuid = 1;
  inline bool has_huuid() const;
  inline void clear_huuid();
  static const int kHuuidFieldNumber = 1;
  inline ::google::protobuf::uint64 huuid() const;
  inline void set_huuid(::google::protobuf::uint64 value);

  // required uint64 luuid = 2;
  inline bool has_luuid() const;
  inline void clear_luuid();
  static const int kLuuidFieldNumber = 2;
  inline ::google::protobuf::uint64 luuid() const;
  inline void set_luuid(::google::protobuf::uint64 value);

  // required uint64 upload_time = 3;
  inline bool has_upload_time() const;
  inline void clear_upload_time();
  static const int kUploadTimeFieldNumber = 3;
  inline ::google::protobuf::uint64 upload_time() const;
  inline void set_upload_time(::google::protobuf::uint64 value);

  // required uint64 sensor_id = 4;
  inline bool has_sensor_id() const;
  inline void clear_sensor_id();
  static const int kSensorIdFieldNumber = 4;
  inline ::google::protobuf::uint64 sensor_id() const;
  inline void set_sensor_id(::google::protobuf::uint64 value);

  // repeated .nervousproto.SensorUpload.SensorData sensor_values = 5;
  inline int sensor_values_size() const;
  inline void clear_sensor_values();
  static const int kSensorValuesFieldNumber = 5;
  inline const ::nervousproto::SensorUpload_SensorData& sensor_values(int index) const;
  inline ::nervousproto::SensorUpload_SensorData* mutable_sensor_values(int index);
  inline ::nervousproto::SensorUpload_SensorData* add_sensor_values();
  inline const ::google::protobuf::RepeatedPtrField< ::nervousproto::SensorUpload_SensorData >&
      sensor_values() const;
  inline ::google::protobuf::RepeatedPtrField< ::nervousproto::SensorUpload_SensorData >*
      mutable_sensor_values();

  // @@protoc_insertion_point(class_scope:nervousproto.SensorUpload)
 private:
  inline void set_has_huuid();
  inline void clear_has_huuid();
  inline void set_has_luuid();
  inline void clear_has_luuid();
  inline void set_has_upload_time();
  inline void clear_has_upload_time();
  inline void set_has_sensor_id();
  inline void clear_has_sensor_id();

  ::google::protobuf::UnknownFieldSet _unknown_fields_;

  ::google::protobuf::uint64 huuid_;
  ::google::protobuf::uint64 luuid_;
  ::google::protobuf::uint64 upload_time_;
  ::google::protobuf::uint64 sensor_id_;
  ::google::protobuf::RepeatedPtrField< ::nervousproto::SensorUpload_SensorData > sensor_values_;

  mutable int _cached_size_;
  ::google::protobuf::uint32 _has_bits_[(5 + 31) / 32];

  friend void  protobuf_AddDesc_sensorupload_2eproto();
  friend void protobuf_AssignDesc_sensorupload_2eproto();
  friend void protobuf_ShutdownFile_sensorupload_2eproto();

  void InitAsDefaultInstance();
  static SensorUpload* default_instance_;
};
// ===================================================================


// ===================================================================

// SensorUpload_SensorData

// required uint64 record_time = 1;
inline bool SensorUpload_SensorData::has_record_time() const {
  return (_has_bits_[0] & 0x00000001u) != 0;
}
inline void SensorUpload_SensorData::set_has_record_time() {
  _has_bits_[0] |= 0x00000001u;
}
inline void SensorUpload_SensorData::clear_has_record_time() {
  _has_bits_[0] &= ~0x00000001u;
}
inline void SensorUpload_SensorData::clear_record_time() {
  record_time_ = GOOGLE_ULONGLONG(0);
  clear_has_record_time();
}
inline ::google::protobuf::uint64 SensorUpload_SensorData::record_time() const {
  return record_time_;
}
inline void SensorUpload_SensorData::set_record_time(::google::protobuf::uint64 value) {
  set_has_record_time();
  record_time_ = value;
}

// repeated bool value_bool = 2 [packed = true];
inline int SensorUpload_SensorData::value_bool_size() const {
  return value_bool_.size();
}
inline void SensorUpload_SensorData::clear_value_bool() {
  value_bool_.Clear();
}
inline bool SensorUpload_SensorData::value_bool(int index) const {
  return value_bool_.Get(index);
}
inline void SensorUpload_SensorData::set_value_bool(int index, bool value) {
  value_bool_.Set(index, value);
}
inline void SensorUpload_SensorData::add_value_bool(bool value) {
  value_bool_.Add(value);
}
inline const ::google::protobuf::RepeatedField< bool >&
SensorUpload_SensorData::value_bool() const {
  return value_bool_;
}
inline ::google::protobuf::RepeatedField< bool >*
SensorUpload_SensorData::mutable_value_bool() {
  return &value_bool_;
}

// repeated int32 value_int32 = 3 [packed = true];
inline int SensorUpload_SensorData::value_int32_size() const {
  return value_int32_.size();
}
inline void SensorUpload_SensorData::clear_value_int32() {
  value_int32_.Clear();
}
inline ::google::protobuf::int32 SensorUpload_SensorData::value_int32(int index) const {
  return value_int32_.Get(index);
}
inline void SensorUpload_SensorData::set_value_int32(int index, ::google::protobuf::int32 value) {
  value_int32_.Set(index, value);
}
inline void SensorUpload_SensorData::add_value_int32(::google::protobuf::int32 value) {
  value_int32_.Add(value);
}
inline const ::google::protobuf::RepeatedField< ::google::protobuf::int32 >&
SensorUpload_SensorData::value_int32() const {
  return value_int32_;
}
inline ::google::protobuf::RepeatedField< ::google::protobuf::int32 >*
SensorUpload_SensorData::mutable_value_int32() {
  return &value_int32_;
}

// repeated int64 value_int64 = 4 [packed = true];
inline int SensorUpload_SensorData::value_int64_size() const {
  return value_int64_.size();
}
inline void SensorUpload_SensorData::clear_value_int64() {
  value_int64_.Clear();
}
inline ::google::protobuf::int64 SensorUpload_SensorData::value_int64(int index) const {
  return value_int64_.Get(index);
}
inline void SensorUpload_SensorData::set_value_int64(int index, ::google::protobuf::int64 value) {
  value_int64_.Set(index, value);
}
inline void SensorUpload_SensorData::add_value_int64(::google::protobuf::int64 value) {
  value_int64_.Add(value);
}
inline const ::google::protobuf::RepeatedField< ::google::protobuf::int64 >&
SensorUpload_SensorData::value_int64() const {
  return value_int64_;
}
inline ::google::protobuf::RepeatedField< ::google::protobuf::int64 >*
SensorUpload_SensorData::mutable_value_int64() {
  return &value_int64_;
}

// repeated float value_float = 5 [packed = true];
inline int SensorUpload_SensorData::value_float_size() const {
  return value_float_.size();
}
inline void SensorUpload_SensorData::clear_value_float() {
  value_float_.Clear();
}
inline float SensorUpload_SensorData::value_float(int index) const {
  return value_float_.Get(index);
}
inline void SensorUpload_SensorData::set_value_float(int index, float value) {
  value_float_.Set(index, value);
}
inline void SensorUpload_SensorData::add_value_float(float value) {
  value_float_.Add(value);
}
inline const ::google::protobuf::RepeatedField< float >&
SensorUpload_SensorData::value_float() const {
  return value_float_;
}
inline ::google::protobuf::RepeatedField< float >*
SensorUpload_SensorData::mutable_value_float() {
  return &value_float_;
}

// repeated double value_double = 6 [packed = true];
inline int SensorUpload_SensorData::value_double_size() const {
  return value_double_.size();
}
inline void SensorUpload_SensorData::clear_value_double() {
  value_double_.Clear();
}
inline double SensorUpload_SensorData::value_double(int index) const {
  return value_double_.Get(index);
}
inline void SensorUpload_SensorData::set_value_double(int index, double value) {
  value_double_.Set(index, value);
}
inline void SensorUpload_SensorData::add_value_double(double value) {
  value_double_.Add(value);
}
inline const ::google::protobuf::RepeatedField< double >&
SensorUpload_SensorData::value_double() const {
  return value_double_;
}
inline ::google::protobuf::RepeatedField< double >*
SensorUpload_SensorData::mutable_value_double() {
  return &value_double_;
}

// repeated string value_string = 7;
inline int SensorUpload_SensorData::value_string_size() const {
  return value_string_.size();
}
inline void SensorUpload_SensorData::clear_value_string() {
  value_string_.Clear();
}
inline const ::std::string& SensorUpload_SensorData::value_string(int index) const {
  return value_string_.Get(index);
}
inline ::std::string* SensorUpload_SensorData::mutable_value_string(int index) {
  return value_string_.Mutable(index);
}
inline void SensorUpload_SensorData::set_value_string(int index, const ::std::string& value) {
  value_string_.Mutable(index)->assign(value);
}
inline void SensorUpload_SensorData::set_value_string(int index, const char* value) {
  value_string_.Mutable(index)->assign(value);
}
inline void SensorUpload_SensorData::set_value_string(int index, const char* value, size_t size) {
  value_string_.Mutable(index)->assign(
    reinterpret_cast<const char*>(value), size);
}
inline ::std::string* SensorUpload_SensorData::add_value_string() {
  return value_string_.Add();
}
inline void SensorUpload_SensorData::add_value_string(const ::std::string& value) {
  value_string_.Add()->assign(value);
}
inline void SensorUpload_SensorData::add_value_string(const char* value) {
  value_string_.Add()->assign(value);
}
inline void SensorUpload_SensorData::add_value_string(const char* value, size_t size) {
  value_string_.Add()->assign(reinterpret_cast<const char*>(value), size);
}
inline const ::google::protobuf::RepeatedPtrField< ::std::string>&
SensorUpload_SensorData::value_string() const {
  return value_string_;
}
inline ::google::protobuf::RepeatedPtrField< ::std::string>*
SensorUpload_SensorData::mutable_value_string() {
  return &value_string_;
}

// -------------------------------------------------------------------

// SensorUpload

// required uint64 huuid = 1;
inline bool SensorUpload::has_huuid() const {
  return (_has_bits_[0] & 0x00000001u) != 0;
}
inline void SensorUpload::set_has_huuid() {
  _has_bits_[0] |= 0x00000001u;
}
inline void SensorUpload::clear_has_huuid() {
  _has_bits_[0] &= ~0x00000001u;
}
inline void SensorUpload::clear_huuid() {
  huuid_ = GOOGLE_ULONGLONG(0);
  clear_has_huuid();
}
inline ::google::protobuf::uint64 SensorUpload::huuid() const {
  return huuid_;
}
inline void SensorUpload::set_huuid(::google::protobuf::uint64 value) {
  set_has_huuid();
  huuid_ = value;
}

// required uint64 luuid = 2;
inline bool SensorUpload::has_luuid() const {
  return (_has_bits_[0] & 0x00000002u) != 0;
}
inline void SensorUpload::set_has_luuid() {
  _has_bits_[0] |= 0x00000002u;
}
inline void SensorUpload::clear_has_luuid() {
  _has_bits_[0] &= ~0x00000002u;
}
inline void SensorUpload::clear_luuid() {
  luuid_ = GOOGLE_ULONGLONG(0);
  clear_has_luuid();
}
inline ::google::protobuf::uint64 SensorUpload::luuid() const {
  return luuid_;
}
inline void SensorUpload::set_luuid(::google::protobuf::uint64 value) {
  set_has_luuid();
  luuid_ = value;
}

// required uint64 upload_time = 3;
inline bool SensorUpload::has_upload_time() const {
  return (_has_bits_[0] & 0x00000004u) != 0;
}
inline void SensorUpload::set_has_upload_time() {
  _has_bits_[0] |= 0x00000004u;
}
inline void SensorUpload::clear_has_upload_time() {
  _has_bits_[0] &= ~0x00000004u;
}
inline void SensorUpload::clear_upload_time() {
  upload_time_ = GOOGLE_ULONGLONG(0);
  clear_has_upload_time();
}
inline ::google::protobuf::uint64 SensorUpload::upload_time() const {
  return upload_time_;
}
inline void SensorUpload::set_upload_time(::google::protobuf::uint64 value) {
  set_has_upload_time();
  upload_time_ = value;
}

// required uint64 sensor_id = 4;
inline bool SensorUpload::has_sensor_id() const {
  return (_has_bits_[0] & 0x00000008u) != 0;
}
inline void SensorUpload::set_has_sensor_id() {
  _has_bits_[0] |= 0x00000008u;
}
inline void SensorUpload::clear_has_sensor_id() {
  _has_bits_[0] &= ~0x00000008u;
}
inline void SensorUpload::clear_sensor_id() {
  sensor_id_ = GOOGLE_ULONGLONG(0);
  clear_has_sensor_id();
}
inline ::google::protobuf::uint64 SensorUpload::sensor_id() const {
  return sensor_id_;
}
inline void SensorUpload::set_sensor_id(::google::protobuf::uint64 value) {
  set_has_sensor_id();
  sensor_id_ = value;
}

// repeated .nervousproto.SensorUpload.SensorData sensor_values = 5;
inline int SensorUpload::sensor_values_size() const {
  return sensor_values_.size();
}
inline void SensorUpload::clear_sensor_values() {
  sensor_values_.Clear();
}
inline const ::nervousproto::SensorUpload_SensorData& SensorUpload::sensor_values(int index) const {
  return sensor_values_.Get(index);
}
inline ::nervousproto::SensorUpload_SensorData* SensorUpload::mutable_sensor_values(int index) {
  return sensor_values_.Mutable(index);
}
inline ::nervousproto::SensorUpload_SensorData* SensorUpload::add_sensor_values() {
  return sensor_values_.Add();
}
inline const ::google::protobuf::RepeatedPtrField< ::nervousproto::SensorUpload_SensorData >&
SensorUpload::sensor_values() const {
  return sensor_values_;
}
inline ::google::protobuf::RepeatedPtrField< ::nervousproto::SensorUpload_SensorData >*
SensorUpload::mutable_sensor_values() {
  return &sensor_values_;
}


// @@protoc_insertion_point(namespace_scope)

}  // namespace nervousproto

#ifndef SWIG
namespace google {
namespace protobuf {


}  // namespace google
}  // namespace protobuf
#endif  // SWIG

// @@protoc_insertion_point(global_scope)

#endif  // PROTOBUF_sensorupload_2eproto__INCLUDED
