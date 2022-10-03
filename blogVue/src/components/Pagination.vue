<template>
  <div>
    <el-pagination
      background
      :page-size="5"
      layout="prev, pager, next"
      @current-change="handleChangePagination"
      :total="dataPagination.totalItems">
    </el-pagination>
  </div>
</template>

<script>
export default {
  name: 'pagination',
  data() {
    return {
      page: 1,
    }
  },
  props: {
    dataPagination: Object
  },
  methods: {
    handleChangePagination(val) {
      this.page = val;
      this.$emit('clickPagination', this.page);
    }
  }
}
</script>